package md.ddbms.proxy.services.caching.implementations;

import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.caching.repositories.MessageHistoryCacheRepository;
import md.ddbms.proxy.services.caching.interfaces.IMessageHistoryCacheService;
import md.ddbms.rmi.models.MessageHistory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MessageHistoryCacheService implements IMessageHistoryCacheService {

    private final MessageHistoryCacheRepository messageHistoryCacheRepository;

    @Override
    public MessageHistory getMessageHistoryFromCache(ArrayList<Integer> usersID) {
        return messageHistoryCacheRepository.findByUsersId(usersID);
    }

    @Override
    public boolean messageHistoryStoredInCache(ArrayList<Integer> usersId) {
        return messageHistoryCacheRepository.findByUsersId(usersId) != null;
    }

    @Override
    public void deleteMessageHistoryFromCache(ArrayList<Integer> usersID) {
        messageHistoryCacheRepository.deleteByUsersId(usersID);
    }

    @Override
    public void pushMessageHistoryToCache(MessageHistory messageHistory) {
        messageHistoryCacheRepository.save(messageHistory);
    }
}
