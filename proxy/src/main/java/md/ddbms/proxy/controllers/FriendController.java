package md.ddbms.proxy.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.caching.services.caching.interfaces.IProfilesCacheService;
import md.ddbms.proxy.models.responses.ErrorResponse;
import md.ddbms.proxy.models.responses.Response;
import md.ddbms.proxy.models.responses.UsersResponse;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.rmi.interfaces.IUserService;
import md.ddbms.rmi.models.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/friends", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class FriendController {
    private final IUserService userService;
    private final IProfilesCacheService profilesCacheService;

    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Get all user's friends")
    @ApiResponse(responseCode = "200", description = "All friends returned",
            content = @Content(schema = @Schema(implementation = UsersResponse.class)))
    @GetMapping
    public ResponseEntity<UsersResponse> getAllFriends() throws NoSuchRMIServiceException,
            ProxyRMIServiceNotFound {

        User user = AuthenticationHelper.getAuthenticatedUser();
        if (profilesCacheService.isUsersResponseStoredInCache(user.getId())) {
            UsersResponse usersResponseFromCache =
                    profilesCacheService.getUsersResponseFromCache(user.getId());
            if (usersResponseFromCache == null) {
                return ResponseEntity.ok(new UsersResponse());
            }
            return ResponseEntity.ok(usersResponseFromCache);
        } else {
            List<User> friends = userService.getAllFriends(user);
            profilesCacheService.addUsersResponseToCache(new UsersResponse(friends), user.getId());
            return ResponseEntity.ok(new UsersResponse(friends));
        }
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Add a friend to user's friend list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Friend has been added to your friends list",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "User is already in friends",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/add")
    public ResponseEntity<Response<String>> addFriend(@Parameter(description = "Added user's id")
                                                      @RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendAlreadyAddedException,
            NoSuchRMIServiceException, ProxyRMIServiceNotFound {

        User user = AuthenticationHelper.getAuthenticatedUser();
        profilesCacheService.deleteUsersResponseFromCache(user.getId());
        userService.addFriend(user, friendId);
        return ResponseEntity.ok(new Response<>("Friend has been added to your friends list"));
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Remove user's friend from friend list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Friend has been removed from your friends list",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "User is not in friends",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping(value = "/remove")
    public ResponseEntity<Response<String>> removeFriend(@Parameter(description = "Removed user's id")
                                                         @RequestParam(name = "userId") int friendId)
            throws UserNotFoundException, FriendDoesNotExistException,
            NoSuchRMIServiceException, ProxyRMIServiceNotFound {

        User user = AuthenticationHelper.getAuthenticatedUser();
        profilesCacheService.deleteUsersResponseFromCache(user.getId());
        userService.removeFriend(user, friendId);
        return ResponseEntity.ok(new Response<>("Friend has been removed from your friends list"));
    }

    @GetMapping(value = "/search")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Search people in user's friend list")
    @ApiResponse(responseCode = "200", description = "Returned friend list according to query",
            content = @Content(schema = @Schema(implementation = UsersResponse.class)))
    public ResponseEntity<UsersResponse> searchFriend(@Parameter(description = "firstname or lastname of a user")
                                                      @RequestParam String searchQuery)
            throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {

        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.searchFriend(user, searchQuery)));
    }
}

