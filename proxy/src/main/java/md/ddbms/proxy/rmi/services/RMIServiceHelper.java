package md.ddbms.proxy.rmi.services;

import lombok.RequiredArgsConstructor;
import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.exceptions.ProxyRMIServiceNotFound;
import md.ddbms.rmi.interfaces.Service;

@RequiredArgsConstructor
public class RMIServiceHelper implements IRMIServiceHelper {
    private final IRMIServiceStorage rmiServiceStorage;

    private IProxyRMIService proxyRMIService;

    @SuppressWarnings("unchecked")
    public <T extends Service> T getService(Class<T> serviceClass) throws ProxyRMIServiceNotFound, NoSuchRMIServiceException {
        proxyRMIService = rmiServiceStorage.getService();
        return (T) proxyRMIService.getRMIService(serviceClass).getObject();
    }

    public void release() {
        rmiServiceStorage.reduceConnection(proxyRMIService);
    }
}
