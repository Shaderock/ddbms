package md.ddbms.bestsn.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.User;
import md.ddbms.bestsn.services.IUserService;
import org.springframework.http.ResponseEntity;
import md.ddbms.bestsn.utils.AuthenticationHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/friends")
@RequiredArgsConstructor
public class FriendController {
    private final IUserService userService;

    @PostMapping(value = "add")
    public ResponseEntity<?> addFriend(@PathVariable(name = "userId") int friendId) throws UserNotFoundException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userService.addFriend(user, friendId);
        return ResponseEntity.ok().build();
    }
}
