package md.ddbms.proxy.controllers.advices;


import md.ddbms.rmi.exceptions.LoginAlreadyExistsException;
import md.ddbms.proxy.controllers.AuthController;
import md.ddbms.proxy.models.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = AuthController.class)
public class AuthControllerAdvice {
    @ExceptionHandler(LoginAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleRegistrationException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }
}
