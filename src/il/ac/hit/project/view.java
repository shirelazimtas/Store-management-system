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
import java.util.Objects;
import java.util.Vector;
/**
 * A class that present create to interaction with the user interface.
 * this class implements the IView.
 */
public class view implements IView {
    private Vector<String> vector;
    private IViewModel vm;
    private JFrame frame;
    private JTextField userName, password, sum, description, newCategory, addNewCurrencyText;
    private JButton login, logout, addNewCost, applyReport, applyNewCategory, addNewCurrency;
    private JPanel northPanel, reportPanel, addCostAndCategoryPanel, tablePanel;
    private GridBagConstraints gbcNorth, gbcEast, gbcCenter;
    private JComboBox <String> category, currency;
    private JDateChooser start, end;
    private JLabel message;
    private JTable table;
    private JScrollPane spTable;
    /**
     * this function allocation space for the variables of the class.
     */
    public void init() {
        vector = new Vector<>(Arrays.asList("Category", "Description", "Sum", "Currency", "Date"));
        frame = new JFrame();
        northPanel = new JPanel(new FlowLayout());
        gbcNorth = new GridBagConstraints();
        userName = new JTextField(10);
        password = new JTextField(10);
        login = new JButton("login");
        logout = new JButton("logout");
        message = new JLabel();
        reportPanel = new JPanel(new FlowLayout());
        gbcEast = new GridBagConstraints();
        start = new JDateChooser();
        end = new JDateChooser();
        applyReport = new JButton("apply");
        addCostAndCategoryPanel = new JPanel(new FlowLayout());
        gbcCenter = new GridBagConstraints();
        sum = new JTextField(10);
        category = new JComboBox<>();
        currency = new JComboBox<>();
        description = new JTextField(10);
        newCategory = new JTextField(20);
        applyNewCategory = new JButton("add new category");
        addNewCurrencyText = new JTextField(20);
        addNewCurrency = new JButton("add new currency");
        addNewCost = new JButton("add new cost");
    }
    /**
     * this function place all the components in the frame and also acting by user's clicking.
     */
    public void start() {
        /*
         *  in north panel has two label for username and password
         *  has two JButton for login and logout
         *  one label that show the message
         */
        gbcNorth.fill = GridBagConstraints.HORIZONTAL;
        northPanel.setLayout(new FlowLayout());
        gbcNorth.insets = new Insets(5, 5, 5, 5);
        gbcNorth.gridx = 10;
        gbcNorth.gridy = 10;
        northPanel.add(new JLabel("Username"), gbcNorth);
        gbcNorth.gridx = 30;
        gbcNorth.gridy = 10;
        userName.setMinimumSize(userName.getPreferredSize());
        northPanel.add(userName, gbcNorth);
        gbcNorth.gridx = 50;
        gbcNorth.gridy = 10;
        northPanel.add(new JLabel("Password"), gbcNorth);
        gbcNorth.gridx = 70;
        gbcNorth.gridy = 10;
        password.setMinimumSize(password.getPreferredSize());
        northPanel.add(password, gbcNorth);
        gbcNorth.gridx = 90;
        gbcNorth.gridy = 10;
        northPanel.add(login, gbcNorth);
        gbcNorth.gridx = 110;
        gbcNorth.gridy = 10;
        northPanel.add(logout, gbcNorth);
        gbcNorth.insets = new Insets(40, 200, 40, 200);
        gbcNorth.gridx = 800;
        gbcNorth.gridy = 10;
        northPanel.add(message, gbcNorth);
        northPanel.setBackground(new Color(255, 250, 205));
        /*
         * in the east side of the panel has one JButton "apply"
         *  and the user can decide the date from and the date until he wants the report
         */
        reportPanel.setLayout(new GridBagLayout());
        gbcEast.insets = new Insets(10, 10, 10, 10);
        gbcEast.gridx = 20;
        reportPanel.add(new JLabel("REPORT"), gbcEast);
        gbcEast.gridx = 10;
        gbcEast.gridy = 10;
        reportPanel.add(new JLabel("From"), gbcEast);
        gbcEast.gridx = 20;
        gbcEast.gridy = 10;
        reportPanel.add(start, gbcEast);
        gbcEast.gridx = 10;
        gbcEast.gridy = 30;
        reportPanel.add(new JLabel("To"), gbcEast);
        gbcEast.gridx = 20;
        gbcEast.gridy = 30;
        reportPanel.add(end, gbcEast);
        gbcEast.gridx = 20;
        gbcEast.gridy = 40;
        reportPanel.add(applyReport, gbcEast);
        reportPanel.setBackground(new Color(255, 248, 220));
        /*
         *  in the center side the user  can type the sum choose the category, currency,description
         *  and add the  item to the table
         *  also he can add a new category
         */
        gbcCenter.fill = GridBagConstraints.HORIZONTAL;
        addCostAndCategoryPanel.setLayout(new GridBagLayout());
        gbcCenter.insets = new Insets(5, 5, 5, 5);
        gbcCenter.gridx = 10;
        gbcCenter.gridy = 10;
        addCostAndCategoryPanel.add(new JLabel("sum"), gbcCenter);
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 10;
        addCostAndCategoryPanel.add(sum, gbcCenter);
        gbcCenter.gridx = 10;
        gbcCenter.gridy = 70;
        addCostAndCategoryPanel.add(new JLabel("category"), gbcCenter);
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 70;
        category.setMaximumRowCount(4);
        addCostAndCategoryPanel.add(category, gbcCenter);
        gbcCenter.gridx = 10;
        gbcCenter.gridy = 130;
        addCostAndCategoryPanel.add(new JLabel("currency"), gbcCenter);
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 130;
        currency.setMaximumRowCount(4);
        addCostAndCategoryPanel.add(currency, gbcCenter);
        gbcCenter.gridx = 10;
        gbcCenter.gridy = 200;
        addCostAndCategoryPanel.add(new JLabel("description"), gbcCenter);
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 200;
        addCostAndCategoryPanel.add(description, gbcCenter);
        gbcCenter.gridx = 10;
        gbcCenter.gridy = 700;
        addCostAndCategoryPanel.add(newCategory, gbcCenter);
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 700;
        applyNewCategory.setBackground(new Color(188, 143, 143));
        addCostAndCategoryPanel.add(applyNewCategory, gbcCenter);
        gbcCenter.gridx = 10;
        gbcCenter.gridy = 750;
        addCostAndCategoryPanel.add(addNewCurrencyText, gbcCenter);
        gbcCenter.gridx = 50;
        gbcCenter.gridy = 750;
        addNewCurrency.setBackground(new Color(188, 143, 143));
        addCostAndCategoryPanel.add(addNewCurrency, gbcCenter);
        gbcCenter.gridx = 10;
        gbcCenter.gridy = 270;
        gbcCenter.gridwidth = 80;
        gbcCenter.fill = GridBagConstraints.HORIZONTAL;
        addNewCost.setBackground(new Color(188, 143, 143));
        addCostAndCategoryPanel.add(addNewCost, gbcCenter);
        addCostAndCategoryPanel.setBackground(new Color(255, 255, 204));
        addCostAndCategoryPanel.setBackground(new Color(255, 255, 204));
        /*
         *  in the west side the user can see the
         *  table of all the item that he was inserted in the table
         */
        tablePanel = new JPanel();
        // hide the report, table and addCostAndCategory panels
        reportPanel.setVisible(false);
        tablePanel.setVisible(false);
        addCostAndCategoryPanel.setVisible(false);
        //add all the panels to the frame and the size of the frame
        frame.add(reportPanel, BorderLayout.EAST);
        frame.add(tablePanel, BorderLayout.WEST);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(addCostAndCategoryPanel, BorderLayout.CENTER);
        frame.setSize(850, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
        //all the actionListener
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create object of account to send to the viewModel.
                Account account = new Account(userName.getText(), password.getText());
                vm.login(account);
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //activate the logout function in the viewModel.
                vm.logout();
            }
        });
        addNewCost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //create a new cost and then send it to the viewModel.
                    Cost cost = new Cost(description.getText(), sum.getText()
                            , Objects.requireNonNull(currency.getSelectedItem()).toString(),
                            new Category(Objects.requireNonNull(category.getSelectedItem()).toString()));
                    vm.addCost(cost);
                } catch (MyException | NullPointerException emptyField) {
                    //show error message in the user interface.
                    showMessage(new Message(emptyField.getMessage()));
                }
            }
        });
        applyNewCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category category;
                try {
                    //create new category and trying to add it in the viewModel.
                    category = new Category(newCategory.getText());
                    vm.addCategory(category);
                } catch (MyException ex) {
                    //show error message in the user interface.
                    showMessage(new Message(ex.getMessage()));
                }
            }
        });
        addNewCurrency.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adding new currency in the viewModel.
                vm.addCurrency(addNewCurrencyText.getText());
            }
        });

        applyReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //convert the date to LocalDate and send request to the viewModel.
                LocalDate localDateStart = start.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localDateEnd = end.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                vm.report(localDateStart, localDateEnd);
            }
        });
    }
    /**
     * connect between the vm and the viewModel object.
     * @param vm The viewModel object.
     */
    public void setIViewModel(IViewModel vm) {
        //setter of the mvvm architecture.
        this.vm = vm;
    }
    /**
     * show new message on the frame.
     * @param message The text will show on the frame.
     */
    @Override
    public void showMessage(Message message) {
        //setter of the mvvm architecture.
        this.message.setText(message.getMessage());
    }
    /**
     * show all the costs of the user in the frame.
     * @param listCosts The vector of all the expense.
     */
    @Override
    public void showCost( Vector<Vector<Object>> listCosts) {
        //create new defaultTableModel from the vector of vectors its gets.
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
     * show all the categories for specific user.
     * @param categories The vector of all the categories.
     */
    @Override
    public void showCategories(Vector<String> categories) {
        //remove all the items in the JComboBox and insert to him all the elements from the vector.
        category.removeAllItems();
        if (categories != null) {
            for (String item : categories) {
                category.addItem(item);
            }
        }
    }
    /**
     * show all the panels after the user login such as: add new cost/ category, report.
     */
    @Override
    public void showPanels()
    {
        //show the panels in the user interface.
        reportPanel.setVisible(true);
        tablePanel.setVisible(true);
        addCostAndCategoryPanel.setVisible(true);
    }
    /**
     * hide all the panels after the user logout such as: add and report panels.
     */
    @Override
    public void hidePanels()
    {
        //hide the panels in the user interface.
        reportPanel.setVisible(false);
        tablePanel.setVisible(false);
        addCostAndCategoryPanel.setVisible(false);
        table.setVisible(false);
    }
    /**
     * show all the currency of specific user.
     * @param currency the vector of all the currencies.
     */
    @Override
    public void showCurrency(Vector<String> currency) {
        //remove all the items in the JComboBox and insert to him all the elements from the vector.
        this.currency.removeAllItems();//
        if (currency != null) {
            for (String item : currency)
                this.currency.addItem(item);
       }
    }
}