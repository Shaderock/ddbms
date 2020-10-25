package md.ddbms.proxy.controllers.advices;

import exceptions.FriendAlreadyAddedException;
import exceptions.FriendDoesNotExistException;
import exceptions.UserNotFoundException;
import md.ddbms.proxy.controllers.FriendController;
import md.ddbms.proxy.models.responses.ErrorResponse;
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
