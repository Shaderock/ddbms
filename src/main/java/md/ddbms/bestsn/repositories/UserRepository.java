package md.ddbms.bestsn.repositories;

import md.ddbms.bestsn.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByLogin(String login);

    Optional<User> findUserById(int id);
}
