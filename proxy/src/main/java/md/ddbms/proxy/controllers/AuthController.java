package md.ddbms.proxy.controllers;

import com.sun.istack.NotNull;
import dtos.UserDTO;
import exceptions.LoginAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.config.jwt.JwtUtils;
import md.ddbms.proxy.models.responses.JwtResponse;
import md.ddbms.proxy.models.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import services.interfaces.IUserService;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class AuthController extends XmlJsonController {
    private final IUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<Response<String>> registerUser(@RequestBody @Valid UserDTO userDTO)
            throws LoginAlreadyExistsException {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userService.create(userDTO);

        return ResponseEntity.ok(new Response<>("Successful registration"));
    }

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
