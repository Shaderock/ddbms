package md.ddbms.proxy.caching.services.caching.interfaces;

import md.ddbms.proxy.models.responses.UsersResponse;

public interface IProfilesCacheService {
    UsersResponse getUsersResponseFromCache(int userId);

    boolean isUsersResponseStoredInCache(int userId);

    void deleteUsersResponseFromCache(int userId);

    void addUsersResponseToCache(UsersResponse usersResponse, int userId);
}
