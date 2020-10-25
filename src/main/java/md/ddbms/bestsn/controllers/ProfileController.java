package md.ddbms.bestsn.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.dtos.UserDTO;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.User;
import md.ddbms.bestsn.models.responses.UserResponse;
import md.ddbms.bestsn.services.interfaces.IUserService;
import md.ddbms.bestsn.utils.AuthenticationHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profile")
@RequiredArgsConstructor
public class ProfileController extends XmlJsonController {
    private final IUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public ResponseEntity<UserResponse> getProfile() {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getProfileById(@PathVariable int id)
            throws UserNotFoundException {
        AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UserResponse(userService.getById(id)));
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateProfile(@RequestBody UserDTO userDTO) {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user = userService.update(user, userDTO);
        return ResponseEntity.ok(new UserResponse(user));
    }
}
