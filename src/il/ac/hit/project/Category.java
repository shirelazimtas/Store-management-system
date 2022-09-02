package il.ac.hit.project;
/**
 * A class that describe a category in expense.
 * @author Lior Avrahimi, Shirel Azim Tas, Malka Azaria.
 */
public class Category {
    //members of the class
    private String category;
    /**
     * Setter of the class member category.
     * @param category the string you want to set.
     * @throws MyException A class that cover all the problem of the program.
     */
    public void setCategory(String category) throws MyException {
        //the right statement check if the string contain numbers if it is so it's throw Exception
        if (category.equals("")||category.matches(".*\\d.*")) {
            throw new MyException("the category field not valid! please fill and try again");
        }
        this.category = category;
    }
    /**
     * Getter for the class member category
     * @return Return the string category.
     */
    public String getCategory() {
        return category;
    }
    /**
     * Constructor that actions the setter.
     * @param category the string you want to set.
     * @throws MyException A class that cover all the problem of the program.
     */
    public Category(String category) throws MyException {
        //activate the setter of the class
        setCategory(category);
    }
}
