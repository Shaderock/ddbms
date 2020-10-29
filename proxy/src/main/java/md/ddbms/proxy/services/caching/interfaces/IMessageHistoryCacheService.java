package md.ddbms.proxy.services.caching.interfaces;

import md.ddbms.rmi.models.MessageHistory;

import java.util.ArrayList;

public interface IMessageHistoryCacheService {

    MessageHistory getMessageHistoryFromCache(ArrayList<Integer> usersID);

    boolean messageHistoryStoredInCache(ArrayList<Integer> usersId);

    void  deleteMessageHistoryFromCache(ArrayList<Integer> usersID);

    void pushMessageHistoryToCache(MessageHistory messageHistory);
}
