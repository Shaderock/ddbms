package md.ddbms.proxy.services.proxies;

import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.rmi.services.IRMIServiceHelper;
import md.ddbms.proxy.rmi.services.IRMIServiceStorage;
import md.ddbms.proxy.rmi.services.RMIServiceHelper;
import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.exceptions.ProxyRMIServiceNotFound;
import md.ddbms.rmi.interfaces.ISequenceGeneratorService;

@RequiredArgsConstructor
public class SequenceGeneratorServiceProxy implements ISequenceGeneratorService {
    private final IRMIServiceStorage rmiServiceStorage;

    @Override
    public int generateSequence(String sequenceName) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        int sequence = rmiServiceHelper.getService(ISequenceGeneratorService.class).generateSequence(sequenceName);
        rmiServiceHelper.release();
        return sequence;
    }
}
