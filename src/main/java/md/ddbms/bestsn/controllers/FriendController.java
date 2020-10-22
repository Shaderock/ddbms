package md.ddbms.bestsn.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.exceptions.FriendAlreadyAddedException;
import md.ddbms.bestsn.exceptions.FriendDoesNotExistException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.User;
import md.ddbms.bestsn.services.IUserService;
import org.springframework.http.ResponseEntity;
import md.ddbms.bestsn.utils.AuthenticationHelper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/friends")
@RequiredArgsConstructor
public class FriendController extends XmlJsonController {
    private final IUserService userService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addFriend(@RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendAlreadyAddedException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.addFriend(user, friendId);
        return ResponseEntity.ok("Friend has been added to your friends list");
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<?> removeFriend(@RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendDoesNotExistException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.removeFriend(user, friendId);
        return ResponseEntity.ok("Friend has been removed from your friends list");
    }
}
