package md.ddbms.bestsn.services.interfaces;

import md.ddbms.bestsn.dtos.MessageDTO;
import md.ddbms.bestsn.exceptions.InconsistentDBException;
import md.ddbms.bestsn.exceptions.MultiChatsException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.User;

public interface IMessageService {
    public void sendMessage(User user, int toUser, MessageDTO messageDTO) throws UserNotFoundException, MultiChatsException, InconsistentDBException;
}
