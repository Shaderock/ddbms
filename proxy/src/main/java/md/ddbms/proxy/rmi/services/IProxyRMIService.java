package md.ddbms.proxy.rmi.services;

import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.exceptions.WrongRMIServiceGenericType;
import md.ddbms.rmi.interfaces.Service;

public interface IProxyRMIService {

    <T extends Service> void createRMIService(Class<T> serviceClass, int rmiServicePort)
            throws WrongRMIServiceGenericType;

    <T extends Service> RMIService<? extends Service> getRMIService(Class<T> serviceClass)
            throws NoSuchRMIServiceException;
}
