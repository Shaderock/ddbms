package md.ddbms.proxy.controllers;

import exceptions.FriendAlreadyAddedException;
import exceptions.FriendDoesNotExistException;
import exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.models.responses.Response;
import md.ddbms.proxy.models.responses.UsersResponse;
import md.ddbms.proxy.utils.AuthenticationHelper;
import models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.interfaces.IUserService;

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
