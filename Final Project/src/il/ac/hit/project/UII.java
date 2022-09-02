package il.ac.hit.project;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Vector;

/**
 * This class manage the user interface
 */
public class UII implements IView {
    String[] columnNames = {"Category","Description","Sum","Currency","Date"};
    Vector<String> vector = new Vector<String>(Arrays.asList(columnNames));
    private IViewModel vm;
    private JFrame frame;
    private JTextField userName,password,sum,currency,description,newCategory;
    private JButton login, logout,addNewCost,applyReport,applyNewCategory;
    private JPanel northPanel, westPanel,eastPanel,addCategoryPanel,tablePanel;
    private JComboBox category;
    private JDateChooser start, end;
    private JLabel message;
    private JTable table;
    private JScrollPane spTable;

    /**
     * This method show the start application screen
     */
    public void start() {
        frame=new JFrame();
        frame.setSize(800,500);
        frame.setLayout(new BorderLayout());

        //login panel
        userName=new JTextField(10);
        password=new JTextField(10);
        login=new JButton("Login");
        logout=new JButton("Logout");
        northPanel = new JPanel(new FlowLayout());
        northPanel.add(new JLabel("UserName"));
        northPanel.add(userName);
        northPanel.add(new JLabel("Password"));
        northPanel.add(password);
        northPanel.add(login);
        northPanel.add(logout);

        //add cost
        westPanel = new JPanel(new GridLayout(5,2,3,4));
        westPanel.add(new JLabel("Sum"));
        sum = new JTextField(10);

        westPanel.add(sum);
        westPanel.add(new JLabel("Category"));
        category=new JComboBox();
        westPanel.add(category);
        westPanel.add(new JLabel("Currency"));
        currency=new JTextField(10);
        westPanel.add(currency);
        westPanel.add(new JLabel("Description"));
        description=new JTextField(10);
        westPanel.add(description);
        addNewCost=new JButton("add new cost");
        westPanel.add(addNewCost);

        //report panel
        eastPanel=new JPanel(new GridLayout(6,1,3,4));
        eastPanel.add(new JLabel("Report"));
        eastPanel.add(new JLabel("From"));
        start=new JDateChooser();
        eastPanel.add(start);
        eastPanel.add(new JLabel("Until"));
        end=new JDateChooser();
        eastPanel.add(end);
        applyReport=new JButton("apply");
        eastPanel.add(applyReport);

        //add category panel
        addCategoryPanel=new JPanel(new FlowLayout());
        newCategory=new JTextField(20);
        addCategoryPanel.add(newCategory);
        applyNewCategory=new JButton("add new category");
        addCategoryPanel.add(applyNewCategory);
        message=new JLabel();
        addCategoryPanel.add(message);

        //table panel
        table=new JTable();
        tablePanel=new JPanel();
        spTable=new JScrollPane(table);
        tablePanel.add(spTable);

        //adding all panels to frame
        //frame.add(tablePanel,BorderLayout.CENTER);
        frame.add(addCategoryPanel,BorderLayout.SOUTH);
        frame.add(eastPanel,BorderLayout.EAST);
        frame.add(westPanel,BorderLayout.WEST);
        frame.add(northPanel,BorderLayout.NORTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account = new Account(userName.getText(), password.getText());
                vm.login(account);
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.logout();
            }
        });
        addNewCost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cost cost = new Cost(description.getText(), Double.parseDouble(sum.getText())
                        , currency.getText(), new Category(category.getSelectedItem().toString()));
                vm.addCost(cost);
            }
        });
        applyNewCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category category = new Category(newCategory.getText());
                vm.addCategory(category);
            }
        });
        applyReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate localDateStart = start.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localDateEnd = end.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                vm.report(localDateStart, localDateEnd);
            }
        });
    }

    /**
     * This method initializing the view model Interface
     * @param vm View model Interface
     */
    public void setIViewModel(IViewModel vm) {
        this.vm = vm;
    }

    /**
     * This method shows a specific message on the screen.
     * This method should invoked on AWT Events thread.  -- ???
     * @param message Message to show
     */
    @Override
    public void showMessage(Message message) {
        this.message.setText(message.getText());
    }

    /**
     * This method shows a cost list of specific user on the screen
     * @param listCosts User'ss cost list
     */
    @Override
    public void showCost( Vector<Vector<Object>> listCosts) {
        frame.remove(spTable);
        spTable.removeAll();
        table=new JTable(listCosts,vector);
        table.setEnabled(false);
        frame.setVisible(true);
        frame.add(spTable.add(table),BorderLayout.CENTER);
        frame.setVisible(true);////
    }

    /**
     * This method shows a categories list of specific user on the screen
     * @param categories User's categories list
     */
    @Override
    public void showCategories(Vector<String> categories) {
    category.removeAllItems();
    for (String item : categories)
            category.addItem(item);
    }
}