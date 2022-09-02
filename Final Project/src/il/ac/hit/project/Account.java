package il.ac.hit.project;

/**
 * This class handle the account settings
 */
public class Account {
    /**
     * This method set user name to account
     * @param userName Name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method set password to account
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;
    private String password;

    /**
     * This method get the user name of the account
     * @return Account's user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method get the password of the account
     * @return Account's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This constructor build user account using setUseName and setPassword methods
     * @param userName Account user name to add
     * @param password Account password to add
     */
    public Account (String userName, String password)
    {
        setUserName(userName);
        setPassword(password);
    }
}
