package md.ddbms.proxy.utils;

import exceptions.NoSuchRMIServiceException;
import services.interfaces.Service;

public interface IRMIServiceHelper {
    <T extends Service> T getReadService(Class<T> serviceClass) throws NoSuchRMIServiceException;

    <T extends Service> T getWriteService(Class<T> serviceClass) throws NoSuchRMIServiceException;
}
