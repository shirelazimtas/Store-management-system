package il.ac.hit.project;
import javax.swing.*;
/**
 * The main class Program "Cost Manger"
 */
public class MainProgram {
    /**
     * The main method (entry point).
     * @param args not relevant.
     */
    public static void main(String[] args) {
        IModel model = null;
        try {
            model = new Model();
        } catch (MyException e) {
            e.printStackTrace();
        }
        IViewModel vm = new ViewModel();
        IView view = new view();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //creating the user interface.
                view.init();
                view.start();
            }
        });
        //implementation of MVVM architecture
        vm.setModel(model);
        vm.setView(view);
        view.setIViewModel(vm);
    }
}
