package md.ddbms.proxy.utils;

import md.ddbms.rmi.exceptions.NoSuchRMIServiceException;
import md.ddbms.rmi.interfaces.Service;

public interface IRMIServiceHelper {
    <T extends Service> T getReadService(Class<T> serviceClass) throws NoSuchRMIServiceException;

    <T extends Service> T getWriteService(Class<T> serviceClass) throws NoSuchRMIServiceException;
}
