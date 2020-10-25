package md.ddbms.data_warehouse.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.data_warehouse.models.responses.UsersResponse;
import md.ddbms.data_warehouse.services.interfaces.IUserService;
import md.ddbms.data_warehouse.utils.AuthenticationHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/people")
public class PeopleController extends XmlJsonController {
    private final IUserService userService;

    @GetMapping(value = "/search")
    public ResponseEntity<UsersResponse> searchPeople(@RequestParam String searchQuery) {
        AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new UsersResponse(userService.search(searchQuery)));
    }
}
