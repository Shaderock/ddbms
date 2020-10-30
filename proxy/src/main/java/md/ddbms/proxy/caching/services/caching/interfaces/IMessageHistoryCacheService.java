package md.ddbms.proxy.caching.services.caching.interfaces;

import md.ddbms.rmi.models.MessageHistory;

public interface IMessageHistoryCacheService {

    MessageHistory getMessageHistoryFromCache(int userId1, int userId2);

    boolean messageHistoryStoredInCache(int userId1, int userId2);

    void  deleteMessageHistoryFromCache(int userId1, int userId2);

    void pushMessageHistoryToCache(MessageHistory messageHistory);
}
