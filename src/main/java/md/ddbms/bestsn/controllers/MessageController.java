package md.ddbms.bestsn.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.dtos.MessageDTO;
import md.ddbms.bestsn.exceptions.InconsistentDBException;
import md.ddbms.bestsn.exceptions.MultiChatsException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.User;
import md.ddbms.bestsn.models.responses.Response;
import md.ddbms.bestsn.services.interfaces.IMessageService;
import md.ddbms.bestsn.utils.AuthenticationHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/message")
public class MessageController extends XmlJsonController {
    private final IMessageService messageService;

    @PostMapping(value = "/send")
    public ResponseEntity<?> sendMessage(@RequestParam(name = "to") int receiverId,
                                         @RequestBody @Valid MessageDTO messageDTO)
            throws UserNotFoundException,
            MultiChatsException,
            InconsistentDBException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        messageService.sendMessage(user, receiverId, messageDTO);
        return ResponseEntity.ok(new Response<>("Message has been sent"));
    }
}
