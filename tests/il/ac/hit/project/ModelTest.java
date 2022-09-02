package il.ac.hit.project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;
import static org.junit.Assert.*;

public class ModelTest {
    private Model mTest;
    {
        try {
            mTest = new Model();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
    private Account account;
    private Cost cost;
    private Category subcategory;
    private LocalDate start;
    private LocalDate end;
    @Before
    public void setUp() throws Exception {
        account=new Account("shirel","2000");
        cost=new Cost("new room","50000","Dollar",new Category("Buy"));
        subcategory=new Category("new house");
        start= LocalDate.of(2020,10,1);
        end=LocalDate.of(2021,12,15);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() throws MyException {
          boolean actual = mTest.login(account);
          assertEquals(true,actual);
    }

    @Test
    public void logout() throws MyException {
        mTest.login(account);
        boolean actual= mTest.logout();
        assertEquals(true,actual);
    }

    @Test
    public void addCost() throws MyException {
        int actual= mTest.addCost(cost);
        assertEquals(1,actual);
    }

    @Test
    public void addCategory() throws MyException {
        int actual= mTest.addCategory(subcategory);
        assertEquals(1,actual);
    }

    @Test
    public void report() throws MyException {
        Vector vector = mTest.report(start, end);
        int numRows=vector.capacity();
        for(int i=0;i<numRows;i++)
        {
            Date valueSingleRowDate = (Date) vector.elementAt(4);
            LocalDate valueSingleRowLocalDate=valueSingleRowDate.toLocalDate();
            assertTrue(start.isBefore(valueSingleRowLocalDate)||start.equals(valueSingleRowLocalDate));
            assertTrue(end.isAfter(valueSingleRowLocalDate)||end.equals(valueSingleRowLocalDate));
        }
    }

    @Test
    public void getListCost() throws MyException, SQLException {
        Vector table=mTest.getListCost();
        int numRows=table.capacity();
        assertEquals(3,numRows);
    }

    @Test
    public void getCategories() throws MyException {
        assertNotNull(mTest.getCategories());
    }
}
