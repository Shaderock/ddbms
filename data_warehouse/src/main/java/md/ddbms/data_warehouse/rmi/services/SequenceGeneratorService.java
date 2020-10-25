package md.ddbms.data_warehouse.rmi.services;

import lombok.RequiredArgsConstructor;
import md.ddbms.data_warehouse.repositories.SequenceGeneratorRepository;
import models.DatabaseSequence;
import org.springframework.stereotype.Service;
import services.interfaces.ISequenceGeneratorService;

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
