package md.ddbms.data_warehouse.controllers.advices;

import md.ddbms.data_warehouse.controllers.MessageController;
import md.ddbms.data_warehouse.exceptions.MessageHistoryNotFoundException;
import md.ddbms.data_warehouse.exceptions.MultiChatsException;
import md.ddbms.data_warehouse.exceptions.UserNotFoundException;
import md.ddbms.data_warehouse.models.responses.ErrorResponse;
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
