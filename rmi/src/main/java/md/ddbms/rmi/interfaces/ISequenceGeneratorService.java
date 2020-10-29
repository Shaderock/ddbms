package md.ddbms.rmi.interfaces;

import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;

public interface ISequenceGeneratorService extends Service {
    int generateSequence(String sequenceName) throws NoSuchRMIServiceException;
}
