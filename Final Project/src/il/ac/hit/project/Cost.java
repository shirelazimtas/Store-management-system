package il.ac.hit.project;

/**
 * This class handle the cost settings
 */
public class Cost {
    private String description;
    private double sum;
    private String currency;
    private Category category;

    /**
     * This method set the description of specific cost
     * @param description Description to add
     * @throws MyException
     */
    public void setDescription(String description) throws MyException {
        if (description.equals("")) {
            throw new MyException();
        }
        this.description = description;
    }

    /**
     * This method set the sum of specific cost
     * @param sum Sum to add
     * @throws MyException
     */
    public void setSum(String sum) throws MyException {
        try{
          this.sum=Double.parseDouble(sum);
        }
          catch (NumberFormatException e)
          {
              throw new MyException("the field sum not valid! please fill and try again");
          }
    }

    /**
     * This method set the currency of specific cost
     * @param currency Currency to add
     * @throws MyException
     */
    public void setCurrency(String currency) throws MyException {
        //the right statement check if the string contain numbers if it is so it's throw Exception
        if (currency.equals("")||currency.matches(".*\\d.*")) {
            throw new MyException("the field currency not valid! please fill and try again");
        }
        this.currency = currency;
    }

    /**
     * This method set the category of specific cost
     * @param category Category to add
     * @throws MyException
     */
    public void setCategory(Category category) throws MyException {
        try {
            this.category = new Category(category.getCategory());
        } catch (MyException e) {
            throw e;
        }
    }

    /**
     * This method get the description of specific cost
     * @return Cost's Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method get the sum of specific cost
     * @return Cost's sum
     */
    public double getSum() {
        return sum;
    }

    /**
     * This method get the currency of specific cost
     * @return Cost's currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * This method get the category of specific cost
     * @return Cost's category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * This constructor build the cost using get methods
     * @param description Cost's description
     * @param sum Cost's sum
     * @param currency Cost's currency
     * @param category Cost's category
     * @throws MyException
     */
    public Cost(String description, String sum, String currency, Category category) throws MyException {
        try {

            setDescription(description);
            setSum(sum);
            setCurrency(currency);
            setCategory(category);
        }
        catch (MyException e)
        {
            throw e;
        }
    }
}