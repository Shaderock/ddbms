package md.ddbms.data_warehouse.services.interfaces;

import md.ddbms.data_warehouse.dtos.MessageDTO;
import md.ddbms.data_warehouse.exceptions.InconsistentDBException;
import md.ddbms.data_warehouse.exceptions.MessageHistoryNotFoundException;
import md.ddbms.data_warehouse.exceptions.MultiChatsException;
import md.ddbms.data_warehouse.exceptions.UserNotFoundException;
import md.ddbms.data_warehouse.models.MessageHistory;
import md.ddbms.data_warehouse.models.User;

import java.util.List;

public interface IMessageService {
    void sendMessage(User user, int toUser, MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException;

    MessageHistory getMessageHistory(User user, int withUserId)
            throws MultiChatsException, InconsistentDBException, UserNotFoundException, MessageHistoryNotFoundException;

    List<MessageHistory> getChatList(User user);
}
