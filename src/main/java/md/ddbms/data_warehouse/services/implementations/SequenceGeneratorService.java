package md.ddbms.data_warehouse.services.implementations;

import lombok.RequiredArgsConstructor;
import md.ddbms.data_warehouse.models.DatabaseSequence;
import md.ddbms.data_warehouse.repositories.SequenceGeneratorRepository;
import md.ddbms.data_warehouse.services.interfaces.ISequenceGeneratorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SequenceGeneratorService implements ISequenceGeneratorService {

    private final SequenceGeneratorRepository sequenceGeneratorRepository;

    @Override
    public int generateSequence(String sequenceName) {
        DatabaseSequence databaseSequence = sequenceGeneratorRepository.findById(sequenceName)
                .orElse(new DatabaseSequence(sequenceName, 1));
        int sequence = databaseSequence.getSequence();
        databaseSequence.IncrementSequence();
        sequenceGeneratorRepository.save(databaseSequence);
        return sequence;
    }
}
