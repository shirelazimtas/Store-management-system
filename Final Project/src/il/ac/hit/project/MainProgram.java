package il.ac.hit.project;

import javax.swing.*;


public class MainProgram {
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
                view.start();
            }
        });
        vm.setModel(model);
        vm.setView(view);
        view.setIViewModel(vm);
    }
}
