package md.ddbms.data_warehouse.controllers;

import lombok.RequiredArgsConstructor;
import md.ddbms.data_warehouse.dtos.MessageDTO;
import md.ddbms.data_warehouse.exceptions.InconsistentDBException;
import md.ddbms.data_warehouse.exceptions.MessageHistoryNotFoundException;
import md.ddbms.data_warehouse.exceptions.MultiChatsException;
import md.ddbms.data_warehouse.exceptions.UserNotFoundException;
import md.ddbms.data_warehouse.models.User;
import md.ddbms.data_warehouse.models.responses.ChatListResponse;
import md.ddbms.data_warehouse.models.responses.MessageHistoryResponse;
import md.ddbms.data_warehouse.models.responses.Response;
import md.ddbms.data_warehouse.services.interfaces.IMessageService;
import md.ddbms.data_warehouse.utils.AuthenticationHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/message")
public class MessageController extends XmlJsonController {
    private final IMessageService messageService;

    @PostMapping(value = "/send")
    public ResponseEntity<Response<String>> sendMessage(@RequestParam(name = "to") int receiverId,
                                         @RequestBody @Valid MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        messageService.sendMessage(user, receiverId, messageDTO);
        return ResponseEntity.ok(new Response<>("Message has been sent"));
    }

    @GetMapping(value = "/history")
    public ResponseEntity<MessageHistoryResponse> getMessageHistory(@RequestParam int withUserId)
            throws UserNotFoundException, MultiChatsException,
            InconsistentDBException, MessageHistoryNotFoundException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(
                new MessageHistoryResponse(messageService.getMessageHistory(user, withUserId), withUserId));
    }

    @GetMapping(value = "/chatlist")
    public ResponseEntity<ChatListResponse> getChatList() {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new ChatListResponse(messageService.getChatList(user), user));
    }
}
