package md.ddbms.data_warehouse.repositories;

import md.ddbms.data_warehouse.models.DatabaseSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SequenceGeneratorRepository extends MongoRepository<DatabaseSequence, String> {

    Optional<DatabaseSequence> findById(String id);
}
