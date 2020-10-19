package md.ddbms.bestsn.controllers.advices;

import md.ddbms.bestsn.controllers.RegistrationController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = RegistrationController.class)
public class RegistrationControllerAdvice {
    public ResponseEntity<String> handleRegistrationException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
