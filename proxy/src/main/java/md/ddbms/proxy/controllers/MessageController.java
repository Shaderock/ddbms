package md.ddbms.proxy.controllers;

import md.ddbms.rmi.dtos.MessageDTO;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.proxy.models.responses.ChatListResponse;
import md.ddbms.proxy.models.responses.MessageHistoryResponse;
import md.ddbms.proxy.models.responses.Response;
import md.ddbms.proxy.services.proxies.MessageServiceProxy;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.proxy.utils.IRMIServiceHelper;
import md.ddbms.rmi.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import md.ddbms.rmi.interfaces.IMessageService;

import javax.validation.Valid;

@RestController
//@RequiredArgsConstructor
@RequestMapping(value = "/message")
public class MessageController extends XmlJsonController {
    private final IMessageService messageService;

    public MessageController(IRMIServiceHelper rmiServiceHelper) {
        this.messageService = new MessageServiceProxy(rmiServiceHelper);
    }


    @PostMapping(value = "/send")
    public ResponseEntity<Response<String>> sendMessage(@RequestParam(name = "to") int receiverId,
                                                        @RequestBody @Valid MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException, NoSuchRMIServiceException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        messageService.sendMessage(user, receiverId, messageDTO);
        return ResponseEntity.ok(new Response<>("Message has been sent"));
    }

    @GetMapping(value = "/history")
    public ResponseEntity<MessageHistoryResponse> getMessageHistory(@RequestParam int withUserId)
            throws UserNotFoundException, MultiChatsException,
            InconsistentDBException, MessageHistoryNotFoundException, NoSuchRMIServiceException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(
                new MessageHistoryResponse(messageService.getMessageHistory(user, withUserId), withUserId));
    }

    @GetMapping(value = "/chatlist")
    public ResponseEntity<ChatListResponse> getChatList() throws NoSuchRMIServiceException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new ChatListResponse(messageService.getChatList(user), user));
    }
}
