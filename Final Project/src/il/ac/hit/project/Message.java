package il.ac.hit.project;

/**
 * This class handle messages get and set text
 */
public class Message {

        private String content;

    /**
     * This method set content to message
     * @param content Message text
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This constructor build the message
     * @param content
     */
    public Message(String content) {
            setContent(content);
        }

    /**
     * This method get content of the message
     * @return Message text
     */
        public String getText() {
            return content;
        }
}
