package services.interfaces;

import dtos.MessageDTO;
import exceptions.InconsistentDBException;
import exceptions.MessageHistoryNotFoundException;
import exceptions.MultiChatsException;
import exceptions.UserNotFoundException;
import models.MessageHistory;
import models.User;

import java.util.List;

public interface IMessageService {
    void sendMessage(User user, int toUser, MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException;

    MessageHistory getMessageHistory(User user, int withUserId)
            throws MultiChatsException, InconsistentDBException, UserNotFoundException, MessageHistoryNotFoundException;

    List<MessageHistory> getChatList(User user);
}
