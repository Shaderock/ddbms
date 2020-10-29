package md.ddbms.proxy.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.proxy.models.responses.UsersResponse;
import md.ddbms.proxy.services.proxies.UserServiceProxy;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.proxy.utils.IRMIServiceHelper;
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
    public ResponseEntity<UsersResponse> searchPeople(@RequestParam String searchQuery)
            throws NoSuchRMIServiceException {
        AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.search(searchQuery)));
    }
}
