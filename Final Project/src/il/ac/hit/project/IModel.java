package il.ac.hit.project;
import java.time.LocalDate;
import java.util.Vector;
public interface IModel {


    public boolean login(Account user) throws MyException;//login to system

    public boolean logout() throws MyException;//logout from system

    public int addCost(Cost item) throws MyException;//adding new cost to DB

    public int addCategory(Category category) throws MyException;//adding new category to DB

    public  Vector<Vector<Object>> report(LocalDate start, LocalDate end) throws MyException;//get specific report

    public  Vector<Vector<Object>> getListCost() throws MyException;//getter costs

    public Vector <String> getCategories() throws MyException;//getter categories

    public void setAccount(Account account);
}