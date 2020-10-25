package md.ddbms.data_warehouse.exceptions;

public class LoginAlreadyExistsException extends Exception {
    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}
