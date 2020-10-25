package md.ddbms.data_warehouse.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.data_warehouse.exceptions.FriendAlreadyAddedException;
import md.ddbms.data_warehouse.exceptions.FriendDoesNotExistException;
import md.ddbms.data_warehouse.exceptions.UserNotFoundException;
import md.ddbms.data_warehouse.models.User;
import md.ddbms.data_warehouse.models.responses.Response;
import md.ddbms.data_warehouse.models.responses.UsersResponse;
import md.ddbms.data_warehouse.services.interfaces.IUserService;
import md.ddbms.data_warehouse.utils.AuthenticationHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/friends")
public class FriendController extends XmlJsonController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<UsersResponse> getAllFriends() {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.getAllFriends(user)));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Response<String>> addFriend(@RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendAlreadyAddedException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.addFriend(user, friendId);
        return ResponseEntity.ok(new Response<>("Friend has been added to your friends list"));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<Response<String>> removeFriend(@RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendDoesNotExistException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.removeFriend(user, friendId);
        return ResponseEntity.ok(new Response<>("Friend has been removed from your friends list"));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<UsersResponse> searchFriend(@RequestParam String searchQuery) {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.searchFriend(user, searchQuery)));
    }
}

