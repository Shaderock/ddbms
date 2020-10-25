package md.ddbms.bestsn.services.interfaces;

import md.ddbms.bestsn.dtos.MessageDTO;
import md.ddbms.bestsn.exceptions.InconsistentDBException;
import md.ddbms.bestsn.exceptions.MessageHistoryNotFoundException;
import md.ddbms.bestsn.exceptions.MultiChatsException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.MessageHistory;
import md.ddbms.bestsn.models.User;

import java.util.List;

public interface IMessageService {
    void sendMessage(User user, int toUser, MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException;

    MessageHistory getMessageHistory(User user, int withUserId)
            throws MultiChatsException, InconsistentDBException, UserNotFoundException, MessageHistoryNotFoundException;

    List<MessageHistory> getChatList(User user);
}
