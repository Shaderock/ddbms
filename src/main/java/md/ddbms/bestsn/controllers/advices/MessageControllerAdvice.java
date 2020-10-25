package md.ddbms.bestsn.controllers.advices;

import md.ddbms.bestsn.controllers.MessageController;
import md.ddbms.bestsn.exceptions.MultiChatsException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = MessageController.class)
public class MessageControllerAdvice {
    @ExceptionHandler({UserNotFoundException.class,
            MultiChatsException.class})
    public ResponseEntity<String> handleMessageActionException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
