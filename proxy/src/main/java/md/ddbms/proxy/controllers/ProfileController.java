package md.ddbms.proxy.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.models.responses.ErrorResponse;
import md.ddbms.proxy.models.responses.UserResponse;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.rmi.dtos.UserDTO;
import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.exceptions.ProxyRMIServiceNotFound;
import md.ddbms.rmi.exceptions.UserNotFoundException;
import md.ddbms.rmi.interfaces.IUserService;
import md.ddbms.rmi.models.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profile", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
public class ProfileController {
    private final IUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Get user's profile information")
    @ApiResponse(responseCode = "200", description = "Returned profile",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    public ResponseEntity<UserResponse> getProfile() {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping(value = "/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Get another user's profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned profile of another user",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserResponse> getProfileById(@Parameter(description = "Another user's id")
                                                       @PathVariable int id)
            throws UserNotFoundException, NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UserResponse(userService.getById(id)));
    }

    @PutMapping
    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Update user's profile")
    @ApiResponse(responseCode = "200", description = "Profile successfully updated",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    public ResponseEntity<UserResponse> updateProfile(@Parameter(description = "New information")
                                                      @RequestBody UserDTO userDTO)
            throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        User user = AuthenticationHelper.getAuthenticatedUser();
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user = userService.update(user, userDTO);
        return ResponseEntity.ok(new UserResponse(user));
    }
}
