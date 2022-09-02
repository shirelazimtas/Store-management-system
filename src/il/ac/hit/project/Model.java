package il.ac.hit.project;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;
/**
 * A class that send sql queries to DB and return data to viewModel.
 * this class implement the IModel
 * @author Lior Avrahimi, Shirel Azim Tas, Malka Azaria.
 */
public class Model implements IModel {
    //members of the class.
    private final String driverFullQualifiedName = "com.mysql.jdbc.Driver";
    private final String connectionString = "jdbc:mysql://localhost:3306/shirel";
    private Connection connection=null;
    private Account account;
    /**
     * Constructor of the class, trying to load all the drivers.
     * @throws MyException A class that cover all the problem of the program.
     */
    public Model() throws MyException {
        try {
            //trying to get the class of the driver.
                Class.forName(driverFullQualifiedName);
            } catch (ClassNotFoundException e) {
                throw new MyException("problem with registering driver to the driver manager",e);
        }
    }
    /**
     * Setter of the class member account
     * @param account The user login details
     */
    @Override
    public void setAccount(Account account)
    {
        this.account=new Account(account.getUserName(), account.getPassword());
    }
    /**
     * send query to the DB to check if the account exist in DB.
     * @param user An object that stores the username and password.
     * @return Return true if exist else throw exception.
     * @throws MyException A class that cover all the problem of the program.
     */
    @Override
    public boolean login(Account user) throws MyException {
        try {
            //create a connection to the DB
            connection = DriverManager.getConnection(connectionString, "shirel", "2000");
            //create a query to get the result set that include the login details of the user.
            String query = "Select * from users Where username='" + user.getUserName() + "' and password='" +
                    user.getPassword()+ "'";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            ResultSet rs;
            rs = preparedStmt.executeQuery();
            //result set empty means that the details' login entered to function incorrect.
            if (!rs.next()){
                throw new MyException("Sorry, your UserName/ Password was incorrect please try again");
            }
            return true;
            //fail to execute the query.
        } catch (SQLException e) {
            throw new MyException("Sorry, our problem. please try again");
        }
    }
    /**
     * Trying to close all connection with the DB.
     * @return Return true if success else throw exception.
     * @throws MyException A class that cover all the problem of the program.
     */
    @Override
    public boolean logout()throws MyException {
        //trying to close all the connection to the DB.
        try {
            account=null;
            connection.close();
            return true;
        //fail to close the connection.
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Sorry, our problem. please try again",e);
        }
    }
    /**
     * Send insert query sql to DB to add a new expense.
     * @param item The object that include all the values of the expanse.
     * @return Return 1 if success to insert else throw exception.
     * @throws MyException A class that cover all the problem of the program.
     */
    @Override
    public int addCost(Cost item)throws MyException {
        try {
            try {
                /*
                at first, the function create a connection to the DB, then it creates a query to
                and insert the values it's gets to the PreparedStatement and execute it.
                 */
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
            //fail to execute the query.
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("problem with add the new cost," +
                        " please sure you entered a valid values at the field and try again!", e);
            //close the connection to the DB.
            } finally {
                connection.close();
            }
        // fail to close the connection.
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Error", e);
        }
    }
    /**
     * Send insert query sql to DB to add a new currency.
     * @param currency A String you want to add t the sql table
     * @return Return 1 if success to insert else throw exception.
     * @throws MyException A class that cover all the problem of the program.
     */
    @Override
    public int addCurrency(String currency) throws MyException {
        /*
        At, first the function create string and insert the value the user want to add
        and then its call the function addingNewType that execute the insert query.
         */
        if(currency.isBlank())
        {
            throw new MyException("problem with adding");
        }
        return addingNewType(" insert into Currency (username, Currency)"
                + " values (?, ?)", currency);
    }
    /**
     * Send insert query sql to DB to add a new category.
     * @param category The object that include string you want to add.
     * @return Return 1 if success to insert else throw exception.
     * @throws MyException A class that cover all the problem of the program.
     */
    @Override
    public int addCategory(Category category) throws MyException {
        /*
        At, first the function create string and insert the value the user want to add
        and then its call the function addingNewType that execute the insert query.
         */
        return addingNewType(" insert into categories (UserName, Category)"
                + " values (?, ?)", category.getCategory());
    }
    /**
     * Execute the sql query.
     * @param query The string of the sql query.
     * @param value The string that you want to add.
     * @return Return 1 if success to insert else throw exception.
     * @throws MyException A class that cover all the problem of the program.
     */
    public int addingNewType(String query, String value) throws MyException {
        try {
            try {
                //create a new connection to the DB with his details' login.
                login(account);
                //create PreparedStatement and execute it.
                PreparedStatement preparedStmtInsert = connection.prepareStatement(query);
                preparedStmtInsert.setString(1, account.getUserName());
                preparedStmtInsert.setString(2, value);
                return preparedStmtInsert.executeUpdate();
            } catch (MySQLIntegrityConstraintViolationException e) //JDBC primary key Exception
            {
                throw new MyException("this value already exist!");
                //fail to execute the query.
            } catch (SQLException e) {
                throw new MyException("problem with adding");
            } finally {
                //close the connection to the DB.
                connection.close();
            }
            //fail to close the connection.
        } catch (SQLException e) {
            throw new MyException("Error");
        }
    }
    /**
     * Create a vector that contain all the expense it's gets in the signature.
     * @param rs The table that create from execute the sql query.
     * @return Return vector of the expenses
     * @throws SQLException Exception that describe error with the get data from the resultSet object.
     */
    public Vector<Vector<Object>> buildTable(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<Vector<Object>> tableData = new Vector<>();
        // data of the table
        Vector<Object> vector;
        while (rs.next()) {
            //create vector that include one row of the resultSet
            vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            //adding one row
            tableData.add(vector);
        }
        return tableData;
    }
    /**
     * Send sql query to gets expenses between dates.
     * @param start Date of the beginning report you want to get data.
     * @param end Date of the end report you want to get data.
     * @return Return Vector that include all the expenses between the dates.
     * @throws MyException A class that cover all the problem of the program.
     */
    @Override
    public  Vector<Vector<Object>> report(LocalDate start, LocalDate end) throws MyException {
        ResultSet st;
        //check if the dates is valid.
        if(start.isAfter(LocalDate.now())|| end.isAfter(LocalDate.now()))
        {
            throw new MyException("The dates you chose is in the future");
        }
        if(start.isAfter(end)) {
            throw new MyException("You need to swap between the dates and try again");
        }
        Vector<Vector<Object>> table;
        try {
            try {
                //create connection and query that gets the costs in those dates.
                login(account);
                String query = "SELECT Category, Description, Sum, Currency,Date FROM costs WHERE Date between ? AND ? And UserName = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setDate(1, Date.valueOf(start));
                preparedStmt.setDate(2, Date.valueOf(end));
                preparedStmt.setString(3, account.getUserName());
                st = preparedStmt.executeQuery();
                //convert the resultSet to Vector<Vector<Object>>.
                table = buildTable(st);
            //fail to execute the query.
            } catch (SQLException e) {
                throw new MyException("problem with export the report, please try again!", e);
            } finally {
                //close the connection to the DB.
                    connection.close();
                }
        //fail to close the connection.
        } catch (SQLException e) {
                    throw new MyException("Error");
            }
        return table;
    }
    /**
     * Gets list of all user expenses.
     * @return Return list of all the user expenses.
     * @throws MyException A class that cover all the problem of the program.
     */
    @Override
    public  Vector<Vector<Object>> getListCost()throws MyException {
        String query = " SELECT Category ,Description, Sum, Currency, Date FROM costs WHERE UserName = ?";
        PreparedStatement preparedStmt;
        ResultSet rs;
        Vector<Vector<Object>> table;
        try {
            try {
                //create connection to the DB and execute the query.
                login(account);
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, account.getUserName());
                rs = preparedStmt.executeQuery();
                //convert the resultSet to Vector<Vector<Object>>.
                table = buildTable(rs);
            //fail to execute the query.
            } catch (SQLException e) {
                throw new MyException("problem with export the costs, please try again!");
            } finally {
                    //close the connection to the DB.
                    connection.close();
                }
        //fail to close the connection.
        }catch (SQLException e) {
            throw new MyException("Error");
        }
        return table;
    }
    /**
     * Gets list of all user categories.
     * @return Return list of all the user categories.
     * @throws MyException A class that cover all the problem of the program.
     */
    @Override
    public Vector <String> getCategories() throws MyException {
                /*
        creates a string query and activate the getListComboBox
        that gets the names of all the user' categories
         */
        String query = " select (Category) from categories WHERE UserName = ?";
        return getListComboBox(query,"Category");
    }
    /**
     * Gets list of all user currency.
     * @return Return list of all the currency.
     * @throws MyException A class that cover all the problem of the program.
     */
    @Override
    public Vector<String> getCurrency() throws MyException {
        /*
        creates a string query and activate the getListComboBox
        that gets the names of all the user' currencies.
         */
        String query = " select (Currency) from currency WHERE UserName = ?";
        return getListComboBox(query,"Currency");
    }
    /**
     * Execute the sql query and transfer from resultSet to vector.
     * @param query The sql query.
     * @param label The name of the column in the DB.
     * @return Return the vector of all the elements in the specific label column.
     * @throws MyException A class that cover all the problem of the program.
     */
    public Vector<String> getListComboBox(String query, String label) throws MyException {
        Vector<String> vector = new Vector<>();
        PreparedStatement preparedStmt;
        ResultSet rs;
        try {
            try {
                //creates a connection and execute query.
                login(account);
                preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, account.getUserName());
                rs = preparedStmt.executeQuery();
                String category;
                /*
                this while loop is to add to the vector all
                the names in the result set that except from the executes query.
                 */
                while (rs.next()) {
                    category = rs.getString(label);
                    vector.add(category);
                }
            //fail to execute the query.
            } catch (SQLException e) {
                throw new MyException("problem with loading ,please try again!", e);
            } finally {
                //close the connection to the DB.
                connection.close();
            }
        //fail to close the connection.
        } catch (SQLException e) {
            throw new MyException("Error", e);
        }
        return vector;
    }
}
