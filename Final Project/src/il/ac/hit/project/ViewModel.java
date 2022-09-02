package il.ac.hit.project;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class handle the connection between Viem to model classes
 */
public class ViewModel implements IViewModel {

    private IModel model;
    private IView view;
    private ExecutorService service;

    /**
     * This constructor build the ViemModel service
     */
    ViewModel() {
        this.service = Executors.newFixedThreadPool(8);
    }

    /**
     * This method set the View object
     * @param view
     */
    public void setView(IView view) {
        this.view = view;
    }

    /**
     * This method set the Model object
     * @param model
     */
    public void setModel(IModel model) {
        this.model = model;
    }

    /**
     * This method doing the logic login and show/hide to screen the specific messages or panels according to user flow
     * @param user User that ask to login
     */
    @Override
    public void login(Account user) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.login(user);
                    model.setAccount(user);
                    Vector<Vector<Object>> listCosts = model.getListCost();
                    Vector<String> categories = model.getCategories();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message("welcome"));
                            view.showPanels();
                            view.showCost(listCosts);
                            view.showCategories(categories);
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    /**
     * This method doing the logic logout and show/hide to screen the specific messages or panels according to user flow
     */
    @Override
    public void logout() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.logout();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message("see you again next time"));
                            view.hidePanels();
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });

    }

    /**
     * This method doing the logic of adding cost and show to screen the user's cost table with the added cost
     * @param item Cost to add
     */
    @Override
    public void addCost(Cost item) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCost(item);
                    Vector<Vector<Object>> listCosts = model.getListCost();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message("a new cost was added"));
                            view.showCost(listCosts);
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void addCategory(Category category) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    int success = model.addCategory(category);
                    Vector listCategories = model.getCategories();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (success != 0) {
                                view.showMessage(new Message("a new category was added"));
                                view.showCategories(listCategories);
                            }
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }

        });
    }

    @Override
    public void report(LocalDate start, LocalDate end) {

        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.report(start, end);
                    Vector<Vector<Object>> listReport = model.report(start, end);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message("The report was created "));
                            view.showCost(listReport);
                        }
                    });
                } catch (MyException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

}