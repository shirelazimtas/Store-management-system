package il.ac.hit.project;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Vector;
/**
 * This class manage the View model
 */
public class view implements IView {

    private Vector<String> vector = new Vector<>(Arrays.asList(new String[]{"Category", "Description", "Sum", "Currency", "Date"}));
    private IViewModel vm;
    private JFrame frame;
    private JTextField userName, password, sum, currency, description, newCategory;
    private JButton login, logout, addNewCost, applyReport, applyNewCategory;
    private JPanel northPanel, reportPanel, addCategoryPanel, tablePanel;
    private GridBagConstraints gbcNorth, gbcEast,gbcCenter;
    private JComboBox category;
    private JDateChooser start, end;
    private JLabel message;
    private JTable table;
    private JScrollPane spTable;
    /**
     * This method show the start application screen
     */
    public void start() {

        //Login & Logout
        frame = new JFrame();
        northPanel = new JPanel(new FlowLayout());

        gbcNorth = new GridBagConstraints();
        gbcNorth.fill = GridBagConstraints.HORIZONTAL;


        northPanel.setLayout(new FlowLayout());


        gbcNorth.insets = new Insets(5, 5, 5, 5);

        gbcNorth.gridx = 10;
        gbcNorth.gridy = 10;
        northPanel.add(new JLabel("Username"), gbcNorth);


        userName = new JTextField(10);
        gbcNorth.gridx = 30;
        gbcNorth.gridy = 10;
        userName.setMinimumSize(userName.getPreferredSize());

        northPanel.add(userName, gbcNorth);

        //passwordText = ;
        gbcNorth.gridx = 50;
        gbcNorth.gridy = 10;
        northPanel.add(new JLabel("Password"), gbcNorth);


        password = new JTextField(10);
        gbcNorth.gridx = 70;
        gbcNorth.gridy = 10;
        password.setMinimumSize(password.getPreferredSize());
        northPanel.add(password, gbcNorth);


        login = new JButton("login");
        gbcNorth.gridx = 90;
        gbcNorth.gridy = 10;
        northPanel.add(login, gbcNorth);

        logout = new JButton("logout");
        gbcNorth.gridx = 110;
        gbcNorth.gridy = 10;
        northPanel.add(logout, gbcNorth);


        gbcNorth.insets = new Insets(40, 200, 40, 200);
        message = new JLabel();
        gbcNorth.gridx = 800;
        gbcNorth.gridy = 10;
        northPanel.add(message, gbcNorth);


        northPanel.setBackground(new Color(255, 250, 205));
        //Report
        //EAST
        reportPanel = new JPanel(new FlowLayout());

        gbcEast = new GridBagConstraints();
        reportPanel.setLayout(new GridBagLayout());

        gbcEast.insets = new Insets(10, 10, 10, 10);
        //reportTitle = ;
        gbcEast.gridx = 10;
        gbcEast.gridy = 10;
        reportPanel.add(new JLabel("Report"), gbcEast);

        start = new JDateChooser();
        gbcEast.gridx = 20;
        gbcEast.gridy = 10;
        reportPanel.add(start, gbcEast);


        gbcEast.gridx = 10;
        gbcEast.gridy = 30;
        reportPanel.add(new JLabel("Until"), gbcEast);

        end = new JDateChooser();
        gbcEast.gridx = 20;
        gbcEast.gridy = 30;
        reportPanel.add(end, gbcEast);

        applyReport = new JButton("apply");
        gbcEast.gridx = 20;
        gbcEast.gridy = 40;
        reportPanel.add(applyReport, gbcEast);
        reportPanel.setBackground(new Color(255, 248, 220));


        addCategoryPanel = new JPanel(new FlowLayout());
        gbcCenter = new GridBagConstraints();
        gbcCenter.fill = GridBagConstraints.HORIZONTAL;
        addCategoryPanel.setLayout(new GridBagLayout());

        gbcCenter.insets = new Insets(5, 5, 5, 5);
        gbcCenter.gridx = 10;
        gbcCenter.gridy = 10;
        addCategoryPanel.add(new JLabel("sum"), gbcCenter);


        sum = new JTextField(10);
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 10;
        addCategoryPanel.add(sum, gbcCenter);

        gbcCenter.gridx = 10;
        gbcCenter.gridy = 70;
        addCategoryPanel.add(new JLabel("category"), gbcCenter);


        category = new JComboBox();
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 70;

        category.setMaximumRowCount(4);

        addCategoryPanel.add(category, gbcCenter);

        gbcCenter.gridx = 10;
        gbcCenter.gridy = 130;
        addCategoryPanel.add(new JLabel("currency"), gbcCenter);

        currency = new JTextField(10);
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 130;
        addCategoryPanel.add(currency, gbcCenter);


        gbcCenter.gridx = 10;
        gbcCenter.gridy = 200;
        addCategoryPanel.add(new JLabel("description"), gbcCenter);

        description = new JTextField(10);
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 200;
        addCategoryPanel.add(description, gbcCenter);


        newCategory = new JTextField(20);
        gbcCenter.gridx = 10;
        gbcCenter.gridy = 700;
        addCategoryPanel.add(newCategory, gbcCenter);

        applyNewCategory = new JButton("add new category");
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 700;
        applyNewCategory.setBackground(new Color(188, 143, 143));
        addCategoryPanel.add(applyNewCategory, gbcCenter);

        addNewCost = new JButton("add new cost");
        gbcCenter.gridx = 10;
        gbcCenter.gridy = 270;
        gbcCenter.gridwidth = 80;
        gbcCenter.fill = GridBagConstraints.HORIZONTAL;
        addNewCost.setBackground(new Color(188, 143, 143));
        addCategoryPanel.add(addNewCost, gbcCenter);

        addCategoryPanel.setBackground(new Color(255, 255, 204));

        tablePanel = new JPanel();
        //dispose all expect northPanel

        reportPanel.setVisible(false);
        tablePanel.setVisible(false);
        addCategoryPanel.setVisible(false);

        frame.add(reportPanel, BorderLayout.EAST);
        frame.add(tablePanel, BorderLayout.WEST);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(addCategoryPanel, BorderLayout.CENTER);

        frame.setSize(850, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
        //ActionListener

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
                try {

                    Cost cost = new Cost(description.getText(), sum.getText()
                            , currency.getText(), new Category(category.getSelectedItem().toString()));
                    vm.addCost(cost);
                }
                catch (MyException emptyField)
                {
                    showMessage(new Message(emptyField.getMessage()));
                }
            }
        });
        applyNewCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category category = null;
                try {
                    category = new Category(newCategory.getText());
                    vm.addCategory(category);
                } catch (MyException ex) {
                    showMessage(new Message(ex.getMessage()));
                }
            }
        });
        applyReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate localDateStart = start.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localDateEnd = end.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                vm.report(localDateStart,localDateEnd);
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
     * This method should invoked on AWT Events thread. -- ???
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
        frame.remove(tablePanel);
        DefaultTableModel tableModel=new DefaultTableModel(listCosts,vector);
        table=new JTable(tableModel);
        spTable=new JScrollPane(table);
        tablePanel =new JPanel();
        tablePanel.add(table.getTableHeader(),BorderLayout.PAGE_START);
        tablePanel.add(spTable,BorderLayout.CENTER);
        frame.add(tablePanel,BorderLayout.WEST);
        frame.setVisible(true);
    }

    /**
     * This method shows a categories list of specific user on the screen
     * @param categories User's categories list
     */
    @Override
    public void showCategories(Vector<String> categories) {
        category.removeAllItems();
        if (categories != null) {
            for (String item : categories)
                category.addItem(item);
        }
    }

    /**
     * This method shows the screen panels
     */
    @Override
    public void showPanels()
    {
        reportPanel.setVisible(true);
        tablePanel.setVisible(true);
        addCategoryPanel.setVisible(true);
    }

    /**
     * This method hide the screen panels
     */
    @Override
    public void hidePanels()
    {
        reportPanel.setVisible(false);
        tablePanel.setVisible(false);
        addCategoryPanel.setVisible(false);
        table.setVisible(false);
    }
}