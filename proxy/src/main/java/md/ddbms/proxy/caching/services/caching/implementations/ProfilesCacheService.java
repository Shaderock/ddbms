package md.ddbms.proxy.caching.services.caching.implementations;

import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.caching.models.CachedProfiles;
import md.ddbms.proxy.caching.repositories.ProfilesCacheRepository;
import md.ddbms.proxy.caching.services.caching.interfaces.IProfilesCacheService;
import md.ddbms.proxy.models.responses.UsersResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfilesCacheService implements IProfilesCacheService {
    private final ProfilesCacheRepository profilesCacheRepository;

    @Override
    public UsersResponse getUsersResponseFromCache(int userId) {
        Optional<CachedProfiles> cachedProfiles = profilesCacheRepository.findById(String.valueOf(userId));
        return cachedProfiles.map(CachedProfiles::getUsersResponse).orElse(null);
    }

    @Override
    public boolean isUsersResponseStoredInCache(int userId) {
        return profilesCacheRepository.findById(String.valueOf(userId)).isPresent();
    }

    @Override
    public void deleteUsersResponseFromCache(int userId) {
        profilesCacheRepository.deleteById(String.valueOf(userId));
    }

    @Override
    public void addUsersResponseToCache(UsersResponse usersResponse, int userId) {
        CachedProfiles cachedProfiles = new CachedProfiles();
        cachedProfiles.setId(String.valueOf(userId));
        cachedProfiles.setUsersResponse(usersResponse);
        profilesCacheRepository.save(cachedProfiles);
    }
}
