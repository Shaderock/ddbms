package md.ddbms.bestsn.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.models.User;
import md.ddbms.bestsn.models.responses.ProfileResponse;
import md.ddbms.bestsn.services.IUserService;
import md.ddbms.bestsn.utils.AuthenticationHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile() {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new ProfileResponse(user.getLogin(), user.getFirstName(), user.getLastName()));
    }
}