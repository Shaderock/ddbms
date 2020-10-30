package md.ddbms.proxy.services.caching.implementations;

import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.caching.models.CachedMessageHistory;
import md.ddbms.proxy.caching.repositories.MessageHistoryCacheRepository;
import md.ddbms.proxy.services.caching.interfaces.IMessageHistoryCacheService;
import md.ddbms.rmi.models.MessageHistory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class MessageHistoryCacheService implements IMessageHistoryCacheService {

    private final MessageHistoryCacheRepository messageHistoryCacheRepository;

    @Override
    public MessageHistory getMessageHistoryFromCache(int userId1, int userId2) {
        Optional<CachedMessageHistory> cachedMessageHistory = messageHistoryCacheRepository
                .findById(calcKey(userId1, userId2));
        if (cachedMessageHistory.isPresent()) {
            return cachedMessageHistory.get().getMessageHistory();
        }

        return null;
    }

    @Override
    public boolean messageHistoryStoredInCache(int userId1, int userId2) {
        return getMessageHistoryFromCache(userId1, userId2) != null;
    }

    @Override
    public void deleteMessageHistoryFromCache(int userId1, int userId2) {
        Optional<CachedMessageHistory> cachedMessageHistory = messageHistoryCacheRepository
                .findById(calcKey(userId1, userId2));
        if (cachedMessageHistory.isPresent()) {
            messageHistoryCacheRepository.delete(cachedMessageHistory.get());
        }
    }

    @Override
    public void pushMessageHistoryToCache(MessageHistory messageHistory) {
        Object[] usersId = messageHistory.getUsersId().toArray();
        CachedMessageHistory cachedMessageHistory = new CachedMessageHistory();
        cachedMessageHistory.setId(calcKey((int) usersId[0], (int) usersId[1]));
        cachedMessageHistory.setMessageHistory(messageHistory);
        messageHistoryCacheRepository.save(cachedMessageHistory);
    }

    private String calcKey(int userId1, int userId2) {
        int min = userId1;
        int max = userId2;
        if (min > max) {
            min = userId2;
            max = userId1;
        }
        return "id" + min + "id" + max;
    }
}
