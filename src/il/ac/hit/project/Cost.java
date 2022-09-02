package il.ac.hit.project;
/**
 * A class that hold one expense of user.
 * @author Lior Avrahimi, Shirel Azim Tas, Malka Azaria.
 */
public class Cost {
    //members of the class
    private String description;
    private double sum;
    private String currency;
    private Category category;
    /**
     * Setter for the class member description.
     * @param description The description of the expense you want to add.
     * @throws MyException A class that cover all the problem of the program.
     */
    public void setDescription(String description) throws MyException {
        //check if the string is empty
        if (description.equals("")) {
            throw new MyException("the field description is empty! please fill and try again");
        }
        this.description = description;
    }
    /**
     * Setter for the class member sum.
     * @param sum the sum of the expense you want to add.
     * @throws MyException A class that cover all the problem of the program.
     */
    public void setSum(String sum) throws MyException {
        try{
            //check if the string can be converted to double.
          this.sum=Double.parseDouble(sum);//casting from String to Double
        }
          catch (NumberFormatException e)// if fail to convert the throw exception.
          {
              throw new MyException("the field sum not valid! please fill and try again");
          }
    }
    /**
     * Setter for the class member currency.
     * @param currency the currency of the expense you want to add.
     * @throws MyException A class that cover all the problem of the program.
     */
    public void setCurrency(String currency) throws MyException {
        //the right statement check if the string contain numbers if it is so it's throw Exception
        if (currency.isBlank()) {
            throw new MyException("the field currency not valid! please fill and try again");
        }
        this.currency = currency;
    }
    /**
     * Setter for the class member category.
     * @param category the category of the expense you want to add.
     * @throws MyException A class that cover all the problem of the program.
     */
    public void setCategory(Category category) throws MyException {
        //create new object of category and save pointer to him.
        this.category = new Category(category.getCategory());
    }
    /**
     * Getter for the class member description.
     * @return Return the string description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Getter for the class member sum.
     * @return Return the double sum.
     */
    public double getSum() {
        return sum;
    }
    /**
     * Getter for the class member currency.
     * @return Return the string currency.
     */
    public String getCurrency() {
        return currency;
    }
    /**
     * Getter for the class member category.
     * @return Return the Category (class) category.
     */
    public Category getCategory() {
        return category;
    }
    /**
     * Constructor of the class, actions the setters.
     * @param description The description of the expense.
     * @param sum The sum of the expense.
     * @param currency The currency of the expense.
     * @param category The category of the expense.
     * @throws MyException A class that cover all the problem of the program.
     */
    public Cost(String description, String sum, String currency, Category category) throws MyException {
        //activate the setters of the class.
        setDescription(description);
        setSum(sum);
        setCurrency(currency);
        setCategory(category);
    }
}