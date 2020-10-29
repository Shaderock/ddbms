package md.ddbms.proxy.rmi.services;

import md.ddbms.proxy.rmi.services.IProxyRMIService;
import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.exceptions.ProxyRMIServiceNotFound;
import md.ddbms.rmi.interfaces.Service;

public interface IRMIServiceStorage {
    <T extends Service> IProxyRMIService getService() throws ProxyRMIServiceNotFound;

    void reduceConnection(IProxyRMIService proxyRMIService);
}
