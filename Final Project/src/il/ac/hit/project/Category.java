package il.ac.hit.project;

/**
 * This class  handle the category settings
 */
public class Category {
    private String category;

    /**
     * This method get the current category
     * @return Category
     */
    public String getCategory() {
        return category;
    }

    /**
     * This method adding category to current user
     * @param category Category to add
     * @throws MyException
     */
    public void setCategory(String category) throws MyException {
        //the right statement check if the string contain numbers if it is so it's throw Exception
        if (category.equals("")||category.matches(".*\\d.*")) {
            throw new MyException("the category field not valid! please fill and try again");
        }
        this.category = category;
    }

    /**
     * This constructor build the category using setCategory method
     * @param category
     * @throws MyException
     */
    public Category(String category) throws MyException {
        try {
            setCategory(category);
        } catch (MyException e) {
            throw e;
        }
    }
}
