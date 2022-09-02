package il.ac.hit.project;
/**
 * A class that hold a message to show to user in the frame.
 * @author Lior Avrahimi, Shirel Azim Tas, Malka Azaria.
 */
public class Message {
    //member of the class.
    private String content;
    /**
     * Setter for the class member content.
     * @param content The text that describe the message.
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * Getter for the class member content.
     * @return Return string of the content message.
     */
    public String getMessage() {
        return content;
    }
    /**
     * Constructor of the class, actions the setter.
     * @param content string of the content message.
     */
    public Message(String content) {
        //activate the setter of the class.
        setContent(content);
    }
}
