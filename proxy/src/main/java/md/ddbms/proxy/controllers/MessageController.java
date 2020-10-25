package md.ddbms.proxy.controllers;

import dtos.MessageDTO;
import exceptions.InconsistentDBException;
import exceptions.MessageHistoryNotFoundException;
import exceptions.MultiChatsException;
import exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.models.responses.ChatListResponse;
import md.ddbms.proxy.models.responses.MessageHistoryResponse;
import md.ddbms.proxy.models.responses.Response;
import md.ddbms.proxy.utils.AuthenticationHelper;
import models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.interfaces.IMessageService;

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
