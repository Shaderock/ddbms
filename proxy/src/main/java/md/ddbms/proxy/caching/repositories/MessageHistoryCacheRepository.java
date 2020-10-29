package md.ddbms.proxy.caching.repositories;

import md.ddbms.rmi.models.MessageHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MessageHistoryCacheRepository extends CrudRepository<MessageHistory, String> {

    MessageHistory findByUsersId(ArrayList<Integer> usersId);

    void deleteByUsersId(ArrayList<Integer> usersId);

}
