package md.ddbms.proxy.controllers.advices;


import md.ddbms.proxy.controllers.MessageController;
import md.ddbms.proxy.models.responses.ErrorResponse;
import md.ddbms.rmi.exceptions.InconsistentDBException;
import md.ddbms.rmi.exceptions.MessageHistoryNotFoundException;
import md.ddbms.rmi.exceptions.MultiChatsException;
import md.ddbms.rmi.exceptions.UserNotFoundException;
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

    @ExceptionHandler(InconsistentDBException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
    }
}
