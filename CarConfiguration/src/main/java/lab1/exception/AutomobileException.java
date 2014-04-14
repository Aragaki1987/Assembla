package lab1.exception;

public class AutomobileException extends Throwable{

    public AutomobileException() {
    }

    public AutomobileException(String message) {
        super(message);
    }

    public AutomobileException(String message, Throwable cause) {
        super(message, cause);
    }
}
