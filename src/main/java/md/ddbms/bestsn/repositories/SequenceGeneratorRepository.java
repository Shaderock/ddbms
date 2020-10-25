package md.ddbms.bestsn.repositories;

import md.ddbms.bestsn.models.DatabaseSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SequenceGeneratorRepository extends MongoRepository<DatabaseSequence, String> {

    Optional<DatabaseSequence> findById(String id);
}
