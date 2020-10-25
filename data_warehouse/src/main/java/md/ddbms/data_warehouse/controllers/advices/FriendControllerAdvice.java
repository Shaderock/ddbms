package md.ddbms.data_warehouse.controllers.advices;

import md.ddbms.data_warehouse.controllers.FriendController;
import md.ddbms.data_warehouse.exceptions.FriendAlreadyAddedException;
import md.ddbms.data_warehouse.exceptions.FriendDoesNotExistException;
import md.ddbms.data_warehouse.exceptions.UserNotFoundException;
import md.ddbms.data_warehouse.models.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = FriendController.class)
public class FriendControllerAdvice {
    @ExceptionHandler({
            FriendAlreadyAddedException.class,
            UserNotFoundException.class,
            FriendDoesNotExistException.class})
    public ResponseEntity<ErrorResponse> handleFriendActionException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }
}
