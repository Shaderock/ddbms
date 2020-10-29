package md.ddbms.rmi.exceptions;

public class LoginAlreadyExistsException extends Exception {
    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}
