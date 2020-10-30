package md.ddbms.proxy.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.models.responses.ChatListResponse;
import md.ddbms.proxy.models.responses.MessageHistoryResponse;
import md.ddbms.proxy.models.responses.Response;
import md.ddbms.proxy.caching.services.caching.interfaces.IMessageHistoryCacheService;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.rmi.dtos.MessageDTO;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.rmi.interfaces.IMessageService;
import md.ddbms.rmi.models.MessageHistory;
import md.ddbms.rmi.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/message")
public class MessageController extends XmlJsonController {
    private final IMessageService messageService;
    private final IMessageHistoryCacheService messageHistoryCacheService;

    @PostMapping(value = "/send")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Response<String>> sendMessage(@RequestParam(name = "to") int receiverId,
                                                        @RequestBody @Valid MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException,
            NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        User user = AuthenticationHelper.getAuthenticatedUser();
        messageService.sendMessage(user, receiverId, messageDTO);

        messageHistoryCacheService.deleteMessageHistoryFromCache(user.getId(), receiverId);

        return ResponseEntity.ok(new Response<>("Message has been sent"));
    }

    @GetMapping(value = "/history")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<MessageHistoryResponse> getMessageHistory(@RequestParam int withUserId)
            throws UserNotFoundException, MultiChatsException,
            InconsistentDBException, MessageHistoryNotFoundException,
            NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        User user = AuthenticationHelper.getAuthenticatedUser();

        if (messageHistoryCacheService.messageHistoryStoredInCache(user.getId(), withUserId)) {
            return ResponseEntity.ok(new MessageHistoryResponse(messageHistoryCacheService
                    .getMessageHistoryFromCache(user.getId(), withUserId), withUserId));
        } else {
            MessageHistory messageHistory = messageService.getMessageHistory(user, withUserId);
            messageHistoryCacheService.pushMessageHistoryToCache(messageHistory);
            return ResponseEntity.ok(new MessageHistoryResponse(messageHistory, withUserId));
        }
    }

    @GetMapping(value = "/chatlist")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ChatListResponse> getChatList()
            throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new ChatListResponse(messageService.getChatList(user), user));
    }
}
