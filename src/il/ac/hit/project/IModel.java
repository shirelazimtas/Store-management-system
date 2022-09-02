package il.ac.hit.project;
import java.time.LocalDate;
import java.util.Vector;
/**
 * Interface that include all the method that do use with the DB
 * @author Lior Avrahimi, Shirel Azim Tas, Malka Azaria.
 */
public interface IModel {
    /**
     * search the user account in the sql table.
     * @param user An object that stores the username and password.
     * @return Return true if it's find the user account in the sql table.
     * @throws MyException A class that cover all the problem of the program.
     */
    boolean login(Account user) throws MyException;
    /**
     * delete the connection and the cache on the user account
     * @return return true if success to delete else throw exception
     * @throws MyException A class that cover all the problem of the program
     */
    boolean logout() throws MyException;
    /**
     * Add the Cost item to the sql table
     * @param item The object that include all the values of the expanse.
     * @return Return 1 if success to insert else throw exception.
     * @throws MyException A class that cover all the problem of the program.
     */
    int addCost(Cost item) throws MyException;
    /**
     * Add category to the sql table
     * @param category The object that include string you want to add.
     * @return Return 1 if success to insert else throw exception.
     * @throws MyException A class that cover all the problem of the program.
     */
    int addCategory(Category category) throws MyException;
    /**
     * Add currency to the sql table
     * @param currency A String you want to add t the sql table
     * @return Return 1 if success to insert else throw exception.
     * @throws MyException A class that cover all the problem of the program.
     */
    int addCurrency(String currency) throws MyException;
    /**
     * Gets report of expenses by dates
     * @param start Date of the beginning report you want to get data.
     * @param end Date of the end report you want to get data.
     * @return List of vector that include all the expense from the sql table
     * @throws MyException A class that cover all the problem of the program.
     */
    Vector<Vector<Object>> report(LocalDate start, LocalDate end) throws MyException;
    /**
     * Gets all the expense from the sql table.
     * @return Return vector of all the expense from the sql table.
     * @throws MyException A class that cover all the problem of the program.
     */
    Vector<Vector<Object>> getListCost() throws MyException;
    /**
     * Gets all the categories from the sql table.
     * @return Return vector of all the categories.
     * @throws MyException A class that cover all the problem of the program.
     */
    Vector <String> getCategories() throws MyException;
    /**
     * Gets all the currencies from the sql table.
     * @return Return vector of all the currencies.
     * @throws MyException A class that cover all the problem of the program.
     */
    Vector <String> getCurrency() throws MyException;
    /**
     * Save the user login account
     * @param account The user login details
     */
     void setAccount(Account account);
}