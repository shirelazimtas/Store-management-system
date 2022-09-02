package il.ac.hit.project;
/**
 * A class that cover all the errors that can be created in the model
 * @author Lior Avrahimi, Shirel Azim Tas, Malka Azaria.
 */
public class MyException extends Exception{
    /**
     * Exception constructor that describe the error.
     * @param message the text that describe the error.
     */
    public MyException(String message)
    {
        super(message);
    }
    /**
     * Exception constructor that describe the error with object Throwable.
     * @param message the text that describe the error.
     * @param cause the reason why you get the exception.
     */
    public MyException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
