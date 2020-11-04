package md.ddbms.proxy.caching.repositories;

import md.ddbms.proxy.caching.models.CachedProfiles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesCacheRepository extends CrudRepository<CachedProfiles, String> {
}
