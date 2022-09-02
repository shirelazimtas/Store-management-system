package il.ac.hit.project;
import java.time.LocalDate;
/**
 * Interface that include the connection between  user actions and the access to the DB.
 * @author Lior Avrahimi, Shirel Azim Tas, Malka Azaria.
 */
public interface IViewModel {
    /**
     * Create connection between the ViewModel and the View.
     * @param view The object that describe the frame
     */
    void setView(IView view);
    /**
     * Create connection between the viewModel and the Model.
     * @param model The object that describe the access to the DB.
     */
    void setModel(IModel model);
    /**
     * Trying to connect to the user account.
     * @param user An object that include the userName and password of the user.
     */
    void login(Account user);
    /**
     * Trying to disconnect the user from the system including access to DB.
     */
    void logout();
    /**
     * Insert Cost item to the sql table.
     * @param item the object that you want to insert.
     */
    void addCost(Cost item);
    /**
     * Insert category to the sql table.
     * @param category The object that you want to insert (include just string).
     */
    void addCategory(Category category);
    /**
     * Insert currency to the sql table.
     * @param currency The string name of the currency you want to insert.
     */
    void addCurrency(String currency);
    /**
     * Gets report of the sql table.
     * @param start Date of the beginning report you want to get data.
     * @param end Date of the end report you want to get data.
     */
    void report(LocalDate start, LocalDate end);
}
