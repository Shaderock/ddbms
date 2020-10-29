package md.ddbms.rmi.interfaces;

import md.ddbms.rmi.dtos.MessageDTO;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.rmi.models.MessageHistory;
import md.ddbms.rmi.models.User;

import java.util.List;

public interface IMessageService extends Service {
    void sendMessage(User user, int toUser, MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException, NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    MessageHistory getMessageHistory(User user, int withUserId)
            throws MultiChatsException, InconsistentDBException, UserNotFoundException, MessageHistoryNotFoundException, NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    List<MessageHistory> getChatList(User user) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound;
}
