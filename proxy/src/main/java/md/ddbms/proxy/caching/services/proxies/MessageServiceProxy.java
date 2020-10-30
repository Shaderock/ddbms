package md.ddbms.proxy.caching.services.proxies;

import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.rmi.services.IRMIServiceHelper;
import md.ddbms.proxy.rmi.services.IRMIServiceStorage;
import md.ddbms.proxy.rmi.services.RMIServiceHelper;
import md.ddbms.rmi.dtos.MessageDTO;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.rmi.interfaces.IMessageService;
import md.ddbms.rmi.models.MessageHistory;
import md.ddbms.rmi.models.User;

import java.util.List;

@RequiredArgsConstructor
public class MessageServiceProxy implements IMessageService {
    private final IRMIServiceStorage rmiServiceStorage;

    @Override
    public void sendMessage(User user, int toUser, MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException,
            NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        rmiServiceHelper.getService(IMessageService.class).sendMessage(user, toUser, messageDTO);
        rmiServiceHelper.release();
    }

    @Override
    public MessageHistory getMessageHistory(User user, int withUserId)
            throws MultiChatsException, InconsistentDBException, UserNotFoundException,
            MessageHistoryNotFoundException, NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        MessageHistory messageHistory = rmiServiceHelper.getService(IMessageService.class)
                .getMessageHistory(user, withUserId);
        rmiServiceHelper.release();
        return messageHistory;
    }

    @Override
    public List<MessageHistory> getChatList(User user) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        List<MessageHistory> messageHistories = rmiServiceHelper.getService(IMessageService.class)
                .getChatList(user);
        rmiServiceHelper.release();
        return messageHistories;
    }
}
