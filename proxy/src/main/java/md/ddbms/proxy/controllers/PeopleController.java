package md.ddbms.proxy.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.models.responses.UsersResponse;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.exceptions.ProxyRMIServiceNotFound;
import md.ddbms.rmi.interfaces.IUserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/people", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class PeopleController {
    private final IUserService userService;

    @GetMapping(value = "/search")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Find people by lastname or firstname")
    @ApiResponse(responseCode = "200", description = "Returned all found people according to query",
            content = @Content(schema = @Schema(implementation = UsersResponse.class)))
    public ResponseEntity<UsersResponse> searchPeople(@RequestParam String searchQuery)
            throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.search(searchQuery)));
    }
}
