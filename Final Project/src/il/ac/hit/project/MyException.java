package il.ac.hit.project;

/**
 * This class handle the project Exceptions
 */
public class MyException extends Exception{
    public MyException(String message)
    {
        super(message);
    }
    public MyException(String message, Throwable cause)
    {
        super(message, cause);
    }
    public MyException(){};
}
