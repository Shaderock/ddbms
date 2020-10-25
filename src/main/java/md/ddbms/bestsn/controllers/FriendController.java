package md.ddbms.bestsn.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.exceptions.FriendAlreadyAddedException;
import md.ddbms.bestsn.exceptions.FriendDoesNotExistException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.User;
import md.ddbms.bestsn.models.responses.Response;
import md.ddbms.bestsn.models.responses.UsersResponse;
import md.ddbms.bestsn.services.interfaces.IUserService;
import md.ddbms.bestsn.utils.AuthenticationHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/friends")
public class FriendController extends XmlJsonController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAllFriends() {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.getAllFriends(user)));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addFriend(@RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendAlreadyAddedException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.addFriend(user, friendId);
        return ResponseEntity.ok(new Response<>("Friend has been added to your friends list"));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<?> removeFriend(@RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendDoesNotExistException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.removeFriend(user, friendId);
        return ResponseEntity.ok(new Response<>("Friend has been removed from your friends list"));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> searchFriend(@RequestParam String searchQuery) {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.searchFriend(user, searchQuery)));
    }
}

