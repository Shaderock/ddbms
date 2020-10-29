package md.ddbms.proxy.controllers;

import exceptions.FriendAlreadyAddedException;
import exceptions.FriendDoesNotExistException;
import exceptions.NoSuchRMIServiceException;
import exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.models.responses.Response;
import md.ddbms.proxy.models.responses.UsersResponse;
import md.ddbms.proxy.services.proxies.UserServiceProxy;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.proxy.utils.IRMIServiceHelper;
import models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.interfaces.IUserService;

@RestController
@RequestMapping(value = "/friends")
public class FriendController extends XmlJsonController {
    private final IUserService userService;

    public FriendController(IRMIServiceHelper rmiServiceHelper) {
        userService = new UserServiceProxy(rmiServiceHelper);
    }

    @GetMapping
    public ResponseEntity<UsersResponse> getAllFriends() throws NoSuchRMIServiceException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.getAllFriends(user)));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Response<String>> addFriend(@RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendAlreadyAddedException, NoSuchRMIServiceException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.addFriend(user, friendId);
        return ResponseEntity.ok(new Response<>("Friend has been added to your friends list"));
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<Response<String>> removeFriend(@RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendDoesNotExistException, NoSuchRMIServiceException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.removeFriend(user, friendId);
        return ResponseEntity.ok(new Response<>("Friend has been removed from your friends list"));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<UsersResponse> searchFriend(@RequestParam String searchQuery) throws NoSuchRMIServiceException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.searchFriend(user, searchQuery)));
    }
}

