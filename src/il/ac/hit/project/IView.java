package il.ac.hit.project;
import java.util.Vector;
/**
 * Interface that include all the methods that connect between the user and DB.
 * @author Lior Avrahimi, Shirel Azim Tas, Malka Azaria.
 */
public interface IView {
    /**
     * function that create all the object in the frame.
     */
    void init();
    /**
     * initializing the frame to the user and the acting when pressing something.
     */
    void start();
    /**
     * Gets connect between the view to the IViewModel.
     * @param vm The viewModel object.
     */
    void setIViewModel(IViewModel vm);
    /**
     * Shows message on the frame.
     * @param message The text will show on the frame.
     */
    void showMessage(Message message);
    /**
     * shows the list of expense on the frame as table.
     * @param listCosts The vector of all the expense.
     */
    void showCost( Vector<Vector<Object>> listCosts);
    /**
     * shows all the categories in the JComboBox.
     * @param categories The vector of all the categories.
     */
    void showCategories(Vector <String> categories);
    /**
     * shows all the currencies in the JComboBox.
     * @param currency the vector of all the currencies.
     */
    void showCurrency(Vector <String> currency);
    /**
     * show all the panels in the frame after the user login.
     */
    void showPanels();
    /**
     * hide all the panels in the frame after the user logout.
     */
    void hidePanels();
}
