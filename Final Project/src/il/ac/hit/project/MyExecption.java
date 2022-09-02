package il.ac.hit.project;
/**
 * This class handle the project Exceptions
 */
public class MyExecption extends Exception{
    public MyExecption(String message)
    {
        super(message);
    }
    public MyExecption(String message, Throwable cause)
    {
        super(message, cause);
    }
    public MyExecption(){};//????
}
