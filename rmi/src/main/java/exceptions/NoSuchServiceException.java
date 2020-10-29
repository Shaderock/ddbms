package exceptions;

public class NoSuchServiceException extends Exception {
    public NoSuchServiceException(String message) {
        super(message);
    }
}
