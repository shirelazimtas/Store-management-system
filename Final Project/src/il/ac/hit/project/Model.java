package il.ac.hit.project;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;


//do connect and close to the connection every single methods

/**
 * This class implement all user operations (login, logout, adding costs/category, etc.)
 *
 */
public class Model implements IModel {
    private String driverFullQualifiedName = "com.mysql.jdbc.Driver";
    private String connectionString = "jdbc:mysql://localhost:3306/shirel";
    private Connection connection=null;
    private Account account;

    /**
     * This constructor doing the connection to driver manager
     * @throws MyException
     */
    public Model() throws MyException {
        try {
                Class.forName(driverFullQualifiedName);
            } catch (ClassNotFoundException e) {
                throw new MyException("problem with registering driver to the driver manager",e);
        }
    }

    /**
     * This method owns the login to my sql,
     * In addition the method verify from that the current user exist in users table
     * @param user Contain user name and password
     * @return True if connected
     * @throws MyException
     */
    @Override
    public boolean login(Account user) throws MyException {
        try {
            connection = DriverManager.getConnection(connectionString, "shirel", "2000");
            String query = "Select * from users Where username='" + user.getUserName() + "' and password='" +
                    user.getPassword()+ "'";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs = null;
            rs = preparedStmt.executeQuery();
            if (!rs.next()){
                throw new MyException("Sorry, your UserName/ Password was incorrect please try again"); // maybe there is another way to get to exception
            }
            return true;
        } catch (SQLException e) {
            throw new MyException("Sorry, our problem. please try again");
        }
    }

    /**
     * This method adding new account
     * @param account User to add
     */
    @Override
    public void setAccount(Account account)
    {
        this.account=new Account(account.getUserName(), account.getPassword());
    }

    /**
     * This method doing the logout
     * @return True if logout successfully
     * @throws MyException
     */
    @Override
    public boolean logout()throws MyException {
        try {
            account=null;
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Sorry, our problem. please try again",e);
        }
    }

    /**
     * This method adding cost to the current user
     * @param item Contain cost details: category, description, sum, currency and date
     * @return Executed query results
     * @throws MyException
     */
    @Override
    public int addCost(Cost item)throws MyException {
            try {
                login(account);
                String query = " insert into costs (UserName, Category, Description, Sum, Currency,Date)"
                        + " values (?, ?, ?, ?, ?,?)";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, account.getUserName());
                preparedStmt.setString(2, item.getCategory().getCategory());
                preparedStmt.setString(3, item.getDescription());
                preparedStmt.setDouble(4, item.getSum());
                preparedStmt.setString(5, item.getCurrency());
                preparedStmt.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
                return preparedStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("problem with add the new cost," +
                        " please sure you entered a valid values at the field and try again!", e);
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new MyException("Error",e);

                }
            }
    }

    /**
     * This method adding category to the current user categories list
     * @param category Category to add
     * @return Executed query results
     * @throws MyException
     */
    @Override
    public int addCategory(Category category)throws MyException {
        try {
            login(account);
        String queryToInsert = " insert into categories (UserName, Category)"
                + " values (?, ?)";
        PreparedStatement preparedStmtInsert = connection.prepareStatement(queryToInsert);
            preparedStmtInsert.setString(1, account.getUserName());
            preparedStmtInsert.setString(2, category.getCategory());
            return preparedStmtInsert.executeUpdate();
        } catch (MySQLIntegrityConstraintViolationException e)//JDBC primary key Exception
        {
            throw new MyException("this category already exist!");
        } catch (SQLException e) {
            throw new MyException("problem with adding new category");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new MyException("Error");
            }
        }
    }

    /**
     * This method build the cost table
     * @param rs Query result from costs
     * @return Table
     * @throws SQLException
     */
    public static  Vector<Vector<Object>> buildTable(ResultSet rs) throws SQLException {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            Vector<Vector<Object>> tableData = new Vector<>();
            // data of the table
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                tableData.add(vector);
            }
            return tableData;
        }
        catch (SQLException e)
        {
            throw e;
        }
    }

    /**
     * This method doing costs report between specific dates
     * @param start Date to start from
     * @param end End date
     * @return Table of costs
     * @throws MyException
     */
    @Override
    public  Vector<Vector<Object>> report(LocalDate start, LocalDate end) throws MyException {
        ResultSet st = null;
        if(start.isAfter(LocalDate.now())|| end.isAfter(LocalDate.now()))
        {
            throw new MyException("The dates you chose is in the future");
        }
        if(start.isAfter(end)) {
            throw new MyException("You need to swap between the dates and try again");
        }
        Vector<Vector<Object>> table;
        try {
            login(account);
            String query ="SELECT Category, Description, Sum, Currency,Date FROM costs WHERE Date between ? AND ? And UserName = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDate(1, Date.valueOf(start));
            preparedStmt.setDate(2, Date.valueOf(end));
            preparedStmt.setString(3, account.getUserName());
            st = preparedStmt.executeQuery();
            table= buildTable(st);

        } catch (SQLException e) {
            throw new MyException("problem with export the report, please try again!",e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new MyException("Error");
            }
        }
        return table;
    }

    /**
     * This method getting all user costs
     * @return User costs table
     * @throws MyException
     */
    @Override
    public  Vector<Vector<Object>> getListCost()throws MyException {
        String query = " SELECT Category ,Description, Sum, Currency, Date FROM costs WHERE UserName = ?"; // return error that need to be 1 column - ?
        PreparedStatement preparedStmt;
        ResultSet rs=null;
        Vector<Vector<Object>> table;
        try {
            login(account);
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, account.getUserName());
            rs = preparedStmt.executeQuery();
            table= buildTable(rs);
        } catch (SQLException e) {
            throw new MyException("problem with export the costs, please try again!");
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new MyException("Error");
            }
        }
        return table;
    }

    /**
     * This method get the user categories
     * @return Vector of user categories
     * @throws MyException
     */
    @Override
    public Vector <String> getCategories() throws MyException {
        Vector <String>vector = new Vector();
        String query = " select (Category) from categories WHERE UserName = ?";
        PreparedStatement preparedStmt = null;
        ResultSet rs = null;
        try {
            login(account);
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, account.getUserName());
            rs = preparedStmt.executeQuery();
            String category;
            while(rs.next())
            {
                category = rs.getString("Category");
                vector.add(category);
            }
        } catch (SQLException e) {
            throw new MyException("problem with loading ,please try again!",e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new MyException("Error",e);
            }
        }
        return vector;
    }
}
