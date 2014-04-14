package lab1.exception;

public class AutomotiveException extends Throwable{

    public AutomotiveException() {
    }

    public AutomotiveException(String message) {
        super(message);
    }

    public AutomotiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
