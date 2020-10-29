package services.interfaces;

import exceptions.NoSuchRMIServiceException;

public interface ISequenceGeneratorService extends Service {
    int generateSequence(String sequenceName) throws NoSuchRMIServiceException;
}
