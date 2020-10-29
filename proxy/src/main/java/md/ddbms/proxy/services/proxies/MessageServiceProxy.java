package md.ddbms.proxy.services.proxies;

import dtos.MessageDTO;
import exceptions.*;
import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.utils.IRMIServiceHelper;
import models.MessageHistory;
import models.User;
import services.interfaces.IMessageService;
import services.interfaces.IUserService;

import java.util.List;

@RequiredArgsConstructor
public class MessageServiceProxy implements IMessageService {
    private final IRMIServiceHelper rmiServiceHelper;

    @Override
    public void sendMessage(User user, int toUser, MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException, NoSuchRMIServiceException {
        rmiServiceHelper.getWriteService(IMessageService.class).sendMessage(user, toUser, messageDTO);
    }

    @Override
    public MessageHistory getMessageHistory(User user, int withUserId)
            throws MultiChatsException, InconsistentDBException, UserNotFoundException, MessageHistoryNotFoundException, NoSuchRMIServiceException {
        return rmiServiceHelper.getReadService(IMessageService.class).getMessageHistory(user, withUserId);
    }

    @Override
    public List<MessageHistory> getChatList(User user) throws NoSuchRMIServiceException {
        return rmiServiceHelper.getReadService(IMessageService.class).getChatList(user);
    }
}
