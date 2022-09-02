package il.ac.hit.project;
import javax.swing.*;
import java.time.LocalDate;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * A class that connect between view (user) and the model(DB).
 * This class implement the IViewModel
 * @author Lior Avrahimi, Shirel Azim Tas, Malka Azaria.
 */
public class ViewModel implements IViewModel {
    //members of the class
    private IModel model;
    private IView view;
    private final ExecutorService service;
    /**
     * Constructor of the class.
     * this method initializing max number of thread pool (default is 5) .
     */
    ViewModel() {
        //limit the pool thread at most 5.
        this.service = Executors.newFixedThreadPool(5);
    }
    /**
     * Create connection between the viewModel to the view.
     * @param view The object that describe the frame
     */
    public void setView(IView view) {
        //setter for the view.
        this.view = view;
    }
    /**
     * Create connection between the viewModel to the model.
     * @param model The object that describe the access to the DB.
     */
    public void setModel(IModel model) {
        //setter for the model.
        this.model = model;
    }
    /**
     * check if the user exist in the DB and loading his expenses and all operation.
     * @param user An object that include the userName and password of the user.
     */
    @Override
    public void login(Account user) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //trying to connect to the DB and then save his login details to the next operation if necessary.
                    model.login(user);
                    model.setAccount(user);
                    //pulls out from the DB the lists of the costs,categories,currency.
                    Vector<Vector<Object>> listCosts = model.getListCost();
                    Vector<String> categories = model.getCategories();
                    Vector<String> currency=model.getCurrency();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            //show the lists that pulled out to the user interface.
                            view.showMessage(new Message("welcome"));
                            view.showPanels();
                            view.showCost(listCosts);
                            view.showCategories(categories);
                            view.showCurrency(currency);
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        //show message to the user about the problem occurred when trying to log in.
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }
    /**
     * Trying to disconnect from the DB, and close all the panels in the frame.
     */
    @Override
    public void logout() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //trying to disconnect from the DB and cut off the connection that still open.
                    model.logout();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            //show message if success.
                            view.showMessage(new Message("see you again next time"));
                            view.hidePanels();
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        //show message to the user interface about the problem.
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });

    }
    /**
     * Insert a new cost in the sql table and show it in the frame.
     * @param item the object that you want to insert.
     */
    @Override
    public void addCost(Cost item) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //trying to add the item it's gets with send request to the model object
                    model.addCost(item);
                    //pulls out the list of the costs.
                    Vector<Vector<Object>> listCosts = model.getListCost();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            //show message about success insert new cost.
                            view.showMessage(new Message("a new cost was added"));
                            //update the table of the costs in the user interface (to show the new cost).
                            view.showCost(listCosts);
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        //show message to the user interface about the problem.
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }
    /**
     * Insert a new category in the sql table and show it in the frame.
     * @param category The object that you want to insert (include just string).
     */
    @Override
    public void addCategory(Category category) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //trying to add new category by send request to the DB with the model.
                    //successToInsert =1 if success to insert else be 0.
                    int successToInsert = model.addCategory(category);
                    //pulls out the list of the categories.
                    Vector<String> listCategories = model.getCategories();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            //check if success to insert
                            if (successToInsert != 0) {
                                //show message of successful and show the list of categories after the insert.
                                view.showMessage(new Message("a new category was added"));
                                view.showCategories(listCategories);
                            }
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        //show message to the user interface about the problem.
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }
    /**
     * Insert a new currency in the sql table and show it in the frame.
     * @param currency The string name of the currency you want to insert.
     */
    @Override
    public void addCurrency(String currency) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //trying to add new currency by send request to the DB with the model.
                    //successToInsert =1 if success to insert else be 0.
                    int successToInsert = model.addCurrency(currency);
                    //pulls out the list of the currency.
                    Vector<String> listCurrency = model.getCurrency();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            //check if success to insert
                            if (successToInsert != 0) {
                                //show message of successful and show the list of currency after the insert.
                                view.showMessage(new Message("a new currency was added"));
                                view.showCurrency(listCurrency);
                            }
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        //show message to the user interface about the problem.
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }

        });
    }
    /**
     * Gets the expenses between the Dates entered from the model and show it in the frame.
     * @param start Date of the beginning report you want to get data.
     * @param end Date of the end report you want to get data.
     */
    @Override
    public void report(LocalDate start, LocalDate end) {

        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //pulls out the list of the costs in specific dates by send request to the DB with the model.
                    Vector<Vector<Object>> listReport = model.report(start, end);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            //show message of success to produce report.
                            view.showMessage(new Message("The report was created "));
                            //show the cost in the table of the user interface.
                            view.showCost(listReport);
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        //show message to the user interface about the problem.
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }
}