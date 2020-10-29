package md.ddbms.proxy.services.proxies;

import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.utils.IRMIServiceHelper;
import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.interfaces.ISequenceGeneratorService;

@RequiredArgsConstructor
public class SequenceGeneratorServiceProxy implements ISequenceGeneratorService {
    private final IRMIServiceHelper rmiServiceHelper;

    @Override
    public int generateSequence(String sequenceName) throws NoSuchRMIServiceException {
        return rmiServiceHelper.getWriteService(ISequenceGeneratorService.class).generateSequence(sequenceName);
    }
}
