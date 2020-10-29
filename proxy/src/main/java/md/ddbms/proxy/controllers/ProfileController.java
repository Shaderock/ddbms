package md.ddbms.proxy.controllers;

import md.ddbms.rmi.dtos.UserDTO;
import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.exceptions.UserNotFoundException;
import md.ddbms.proxy.models.responses.UserResponse;
import md.ddbms.proxy.services.proxies.UserServiceProxy;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.proxy.utils.IRMIServiceHelper;
import md.ddbms.rmi.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import md.ddbms.rmi.interfaces.IUserService;

@RestController
@RequestMapping(value = "/profile")
//@RequiredArgsConstructor
public class ProfileController extends XmlJsonController {
    private final IUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ProfileController(IRMIServiceHelper rmiServiceHelper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = new UserServiceProxy(rmiServiceHelper);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getProfile() {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> getProfileById(@PathVariable int id)
            throws UserNotFoundException, NoSuchRMIServiceException {
        AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UserResponse(userService.getById(id)));
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateProfile(@RequestBody UserDTO userDTO) throws NoSuchRMIServiceException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user = userService.update(user, userDTO);
        return ResponseEntity.ok(new UserResponse(user));
    }
}
