package md.ddbms.proxy.controllers.advices;


import exceptions.MessageHistoryNotFoundException;
import exceptions.MultiChatsException;
import exceptions.UserNotFoundException;
import md.ddbms.proxy.controllers.MessageController;
import md.ddbms.proxy.models.responses.ErrorResponse;
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
