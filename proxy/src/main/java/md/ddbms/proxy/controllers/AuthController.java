package md.ddbms.proxy.controllers;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.config.jwt.JwtUtils;
import md.ddbms.proxy.models.responses.ErrorResponse;
import md.ddbms.proxy.models.responses.JwtResponse;
import md.ddbms.proxy.models.responses.Response;
import md.ddbms.rmi.dtos.UserDTO;
import md.ddbms.rmi.exceptions.LoginAlreadyExistsException;
import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.exceptions.ProxyRMIServiceNotFound;
import md.ddbms.rmi.interfaces.IUserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class AuthController {
    private final IUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Operation(summary = "Register a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful registration",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Login already exists",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<Response<String>> registerUser(@Parameter(description = "Information about user")
                                                         @RequestBody @Valid UserDTO userDTO)
            throws LoginAlreadyExistsException, NoSuchRMIServiceException, ProxyRMIServiceNotFound {

        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userService.create(userDTO);

        return ResponseEntity.ok(new Response<>("Successful registration"));
    }

    @Operation(summary = "Authenticate a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful authentication",
                    content = @Content(schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid credentials",
                    content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestParam @NotNull String login,
                                                 @RequestParam @NotNull String password) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
