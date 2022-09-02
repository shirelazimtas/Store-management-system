package il.ac.hit.project;

import java.util.Vector;

public interface IView {
    public void start();
    public void setIViewModel(IViewModel vm);
    public void showMessage(Message message);
    public void showCost( Vector<Vector<Object>> listCosts);
    public void showCategories(Vector <String> categories);
    public void showPanels();
    public void hidePanels();

}
