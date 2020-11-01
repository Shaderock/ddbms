package md.ddbms.proxy.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.caching.services.caching.interfaces.IMessageHistoryCacheService;
import md.ddbms.proxy.models.responses.ChatListResponse;
import md.ddbms.proxy.models.responses.ErrorResponse;
import md.ddbms.proxy.models.responses.MessageHistoryResponse;
import md.ddbms.proxy.models.responses.Response;
import md.ddbms.proxy.utils.AuthenticationHelper;
import md.ddbms.rmi.dtos.MessageDTO;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.rmi.interfaces.IMessageService;
import md.ddbms.rmi.models.MessageHistory;
import md.ddbms.rmi.models.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/message", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class MessageController {
    private final IMessageService messageService;
    private final IMessageHistoryCacheService messageHistoryCacheService;

    @PostMapping(value = "/send")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Send a message to someone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message has been sent",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Multi-chats are not allowed",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "DB has changed during transaction; " +
                    "not found MessageHistory that was present)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Response<String>> sendMessage(@Parameter(description = "Id of the user who will receive the message")
                                                        @RequestParam(name = "to") int receiverId,
                                                        @Parameter(description = "Message to send")
                                                        @RequestBody @Valid MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException,
            NoSuchRMIServiceException, ProxyRMIServiceNotFound {

        User user = AuthenticationHelper.getAuthenticatedUser();
        messageService.sendMessage(user, receiverId, messageDTO);

        messageHistoryCacheService.deleteMessageHistoryFromCache(user.getId(), receiverId);

        return ResponseEntity.ok(new Response<>("Message has been sent"));
    }

    @GetMapping(value = "/history")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Get the entire message history with a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned message history",
                    content = @Content(schema = @Schema(implementation = MessageHistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Message history not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "DB has changed during transaction " +
                    "(not found MessageHistory that was present)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<MessageHistoryResponse> getMessageHistory(
            @Parameter(description = "Id of the user " + "with whom there was communication")
            @RequestParam int withUserId)
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
    @Operation(security = @SecurityRequirement(name = "bearerAuth"),
            summary = "The list of message histories id in which user participated")
    @ApiResponse(responseCode = "200", description = "Returned all chats",
            content = @Content(schema = @Schema(implementation = ChatListResponse.class)))
    public ResponseEntity<ChatListResponse> getChatList()
            throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {

        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new ChatListResponse(messageService.getChatList(user), user));
    }
}
