package il.ac.hit.project;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public interface IViewModel {

    public void setView(IView view);
    public void setModel(IModel model);

    public void login(Account user);//login to system

    public void logout();//logout from system

    public void addCost(Cost item);//adding new cost to DB

    public void addCategory(Category category);//adding new category to DB

    public void report(LocalDate start, LocalDate end);//get specific report
}
