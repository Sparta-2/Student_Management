package error;

public class InvalidSessionException extends Exception {
    public InvalidSessionException(String message) {
        super(message);
    }
}