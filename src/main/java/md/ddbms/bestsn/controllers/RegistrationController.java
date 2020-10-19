package md.ddbms.bestsn.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.dtos.UserDTO;
import md.ddbms.bestsn.exceptions.LoginAlreadyExistsException;
import md.ddbms.bestsn.models.responses.Response;
import md.ddbms.bestsn.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response<String>> registerUser(@RequestBody @Valid UserDTO userDTO)
            throws LoginAlreadyExistsException {
        userService.create(userDTO);

        return ResponseEntity.ok(new Response<>("Successful registration"));
    }
}
