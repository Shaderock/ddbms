package md.ddbms.proxy.rmi.services;

import exceptions.NoSuchRMIServiceException;
import exceptions.WrongRMIServiceGenericType;
import services.interfaces.Service;

import java.util.HashMap;
import java.util.Map;

public class ProxyRMIService implements IProxyRMIService {
    private final Map<Class<? extends Service>, RMIService<? extends Service>> rmiServiceMap = new HashMap<>();

    // Attention!
    // Usage ex.:
    //  setRMIService(IUserService.class, 51000);
    @Override
    public <T extends Service> void createRMIService(Class<T> serviceClass, int rmiServicePort)
            throws WrongRMIServiceGenericType {
        RMIService<T> rmiService = new RMIService<>(rmiServicePort, serviceClass);
        rmiService.afterPropertiesSet();
        rmiServiceMap.put(serviceClass, rmiService);
    }

    // Attention!
    // Usage ex.:
    //  getRMIService(IUserService.class);
    @Override
    public <T extends Service> RMIService<? extends Service> getRMIService(Class<T> serviceClass)
            throws NoSuchRMIServiceException {
        RMIService<? extends Service> rmiService = rmiServiceMap.get(serviceClass);
        if (rmiService == null) {
            throw new NoSuchRMIServiceException("The RMI service of class = '"
                    + serviceClass.getSimpleName() + "' was not found in list of RMI services");
        }
        return rmiService;
    }
}
