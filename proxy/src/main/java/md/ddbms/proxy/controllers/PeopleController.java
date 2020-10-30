package md.ddbms.proxy.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.proxy.models.responses.UsersResponse;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.rmi.exceptions.ProxyRMIServiceNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import md.ddbms.rmi.interfaces.IUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/people")
public class PeopleController extends XmlJsonController {
    private final IUserService userService;

    @GetMapping(value = "/search")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UsersResponse> searchPeople(@RequestParam String searchQuery)
            throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.search(searchQuery)));
    }
}
