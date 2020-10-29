package md.ddbms.proxy.rmi.services;


import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.exceptions.ProxyRMIServiceNotFound;
import md.ddbms.rmi.interfaces.Service;

public interface IRMIServiceHelper {
    <T extends Service> T getService(Class<T> serviceClass) throws ProxyRMIServiceNotFound, NoSuchRMIServiceException;

    void release();
}
