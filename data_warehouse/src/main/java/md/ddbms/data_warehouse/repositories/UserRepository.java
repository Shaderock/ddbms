package md.ddbms.data_warehouse.repositories;

import md.ddbms.rmi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByLogin(String login);

    Optional<User> findById(int id);

    List<User> findByIdIn(Collection<Integer> id);

    List<User> findByLoginContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String loginQuery,
                                                                                                  String firstNameQuery,
                                                                                                  String lastNameQuery);
}
