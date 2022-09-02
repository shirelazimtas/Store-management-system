package il.ac.hit.project;
/**
 * A class that describe an account of the user.
 * @author Lior Avrahimi, Shirel Azim Tas, Malka Azaria.
 */
public class Account {
    //members of the class
    private String userName;
    private String password;
    /**
     * Setter for the class member userName.
     * @param userName The string name of the user account.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Setter for the class member password.
     * @param password The string password of the user account.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Getter for the class member userName.
     * @return Return the string userName of the user account.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Getter for the class member password.
     * @return Return the string password of the user account.
     */
    public String getPassword() {
        return password;
    }
    /**
     * constructor of the class, actions the setters.
     * @param userName The string userName of the user account.
     * @param password The string password of the user account.
     */
    public Account (String userName, String password)
    {
        //activate the setters of the class
        setUserName(userName);
        setPassword(password);
    }
}
