package md.ddbms.bestsn.controllers.advices;

import md.ddbms.bestsn.controllers.FriendController;
import md.ddbms.bestsn.exceptions.FriendAlreadyAddedException;
import md.ddbms.bestsn.exceptions.FriendDoesNotExistException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.responses.ErrorResponse;
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
