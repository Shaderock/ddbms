package services.interfaces;

import dtos.MessageDTO;
import exceptions.*;
import models.MessageHistory;
import models.User;

import java.util.List;

public interface IMessageService extends Service {
    void sendMessage(User user, int toUser, MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException, NoSuchRMIServiceException;

    MessageHistory getMessageHistory(User user, int withUserId)
            throws MultiChatsException, InconsistentDBException, UserNotFoundException, MessageHistoryNotFoundException, NoSuchRMIServiceException;

    List<MessageHistory> getChatList(User user) throws NoSuchRMIServiceException;
}
