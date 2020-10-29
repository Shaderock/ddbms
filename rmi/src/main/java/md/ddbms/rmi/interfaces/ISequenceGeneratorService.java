package md.ddbms.rmi.interfaces;

import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.exceptions.ProxyRMIServiceNotFound;

public interface ISequenceGeneratorService extends Service {
    int generateSequence(String sequenceName) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound;
}
