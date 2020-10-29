package md.ddbms.proxy.caching.repositories;

import md.ddbms.rmi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCacheRepository extends CrudRepository<User, String> {

}
