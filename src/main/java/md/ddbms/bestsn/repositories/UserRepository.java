package md.ddbms.bestsn.repositories;

import md.ddbms.bestsn.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByLogin(String login);

    Optional<User> findById(int id);

    List<User> findByIdIn(Collection<Integer> id);

    List<User> findByLoginContainingOrFirstNameContainingOrLastNameContaining(String loginQuery,
                                                                              String firstNameQuery,
                                                                              String lastNameQuery);
}
