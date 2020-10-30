package md.ddbms.proxy.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.proxy.models.responses.Response;
import md.ddbms.proxy.models.responses.UsersResponse;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.rmi.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import md.ddbms.rmi.interfaces.IUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/friends")
public class FriendController extends XmlJsonController {
    private final IUserService userService;

    @GetMapping
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UsersResponse> getAllFriends() throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.getAllFriends(user)));
    }

    @PostMapping(value = "/add")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Response<String>> addFriend(@RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendAlreadyAddedException,
            NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.addFriend(user, friendId);
        return ResponseEntity.ok(new Response<>("Friend has been added to your friends list"));
    }

    @DeleteMapping(value = "/remove")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Response<String>> removeFriend(@RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendDoesNotExistException,
            NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.removeFriend(user, friendId);
        return ResponseEntity.ok(new Response<>("Friend has been removed from your friends list"));
    }

    @GetMapping(value = "/search")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UsersResponse> searchFriend(@RequestParam String searchQuery)
            throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.searchFriend(user, searchQuery)));
    }
}

