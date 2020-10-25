package md.ddbms.bestsn.controllers.advices;

import md.ddbms.bestsn.controllers.MessageController;
import md.ddbms.bestsn.exceptions.MessageHistoryNotFoundException;
import md.ddbms.bestsn.exceptions.MultiChatsException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = MessageController.class)
public class MessageControllerAdvice {
    @ExceptionHandler({UserNotFoundException.class,
            MultiChatsException.class,
            MessageHistoryNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleMessageActionException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }
}
