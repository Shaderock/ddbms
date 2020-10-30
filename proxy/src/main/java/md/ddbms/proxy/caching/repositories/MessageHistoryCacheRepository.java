package md.ddbms.proxy.caching.repositories;

import md.ddbms.proxy.caching.models.CachedMessageHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MessageHistoryCacheRepository extends CrudRepository<CachedMessageHistory, String> {
}
