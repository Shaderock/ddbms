package md.ddbms.bestsn.services.implementations;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.dtos.MessageDTO;
import md.ddbms.bestsn.exceptions.InconsistentDBException;
import md.ddbms.bestsn.exceptions.MultiChatsException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.MessageHistory;
import md.ddbms.bestsn.models.User;
import md.ddbms.bestsn.repositories.MessageHistoryRepository;
import md.ddbms.bestsn.services.interfaces.IMessageService;
import md.ddbms.bestsn.services.interfaces.ISequenceGeneratorService;
import md.ddbms.bestsn.services.interfaces.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {
    private final IUserService userService;
    private final ISequenceGeneratorService sequenceGeneratorService;
    private final MessageHistoryRepository messageHistoryRepository;

    @Override
    @Transactional
    public void sendMessage(User sender, int receiverId, MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException {
        MessageHistory messageHistory = getMessageHistory(sender, receiverId);

        messageHistory.addMessage(messageDTO.toMessage(messageHistory.getUsersId().toArray()[0].equals(sender)));
        messageHistoryRepository.save(messageHistory);
    }

    @Override
    public MessageHistory getMessageHistory(User user, int withUserId)
            throws MultiChatsException, InconsistentDBException, UserNotFoundException {
        User receiver = userService.getById(withUserId);
        List<MessageHistory> messageHistorySenderList = messageHistoryRepository
                .findByUsersId(user.getId());

        List<MessageHistory> messageHistoryReceiverList = messageHistoryRepository
                .findByUsersId(receiver.getId());

        messageHistorySenderList.retainAll(messageHistoryReceiverList);

        MessageHistory messageHistory;

        if (messageHistorySenderList.size() == 0) {
            messageHistory = new MessageHistory(sequenceGeneratorService.generateSequence(MessageHistory.SEQUENCE_NAME),
                    user, receiver);
        } else if (messageHistorySenderList.size() == 1) {
            messageHistory = messageHistoryRepository
                    .findById(messageHistorySenderList.get(0).getId())
                    .orElseThrow(() ->
                            new InconsistentDBException("DB has changed during transaction " +
                                    "(not found MessageHistory that was present)"));
        } else {
            throw new MultiChatsException("Multiple chats for users with id = [" + user.getId() +
                    ", " + withUserId + "] has found");
        }

        return messageHistory;
    }
}
