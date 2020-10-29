package md.ddbms.proxy.rmi.services;

import exceptions.NoSuchRMIServiceException;
import exceptions.WrongRMIServiceGenericType;
import services.interfaces.Service;

public interface IProxyRMIService {

    <T extends Service> void createRMIService(Class<T> serviceClass, int rmiServicePort)
            throws WrongRMIServiceGenericType;

    <T extends Service> RMIService<? extends Service> getRMIService(Class<T> serviceClass)
            throws NoSuchRMIServiceException;
}
