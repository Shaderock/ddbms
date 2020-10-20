package md.ddbms.bestsn.controllers.advices;

import md.ddbms.bestsn.controllers.AuthController;
import md.ddbms.bestsn.exceptions.LoginAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = AuthController.class)
public class AuthControllerAdvice {
    @ExceptionHandler(LoginAlreadyExistsException.class)
    public ResponseEntity<String> handleRegistrationException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
