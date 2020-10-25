package md.ddbms.bestsn.repositories;

import md.ddbms.bestsn.models.MessageHistory;
import md.ddbms.bestsn.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MessageHistoryRepository extends MongoRepository<MessageHistory, String> {

    List<MessageHistory> findByUsersId(int userId);

    Optional<MessageHistory> findById(int id);
}
