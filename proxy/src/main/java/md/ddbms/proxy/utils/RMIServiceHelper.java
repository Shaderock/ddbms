package md.ddbms.proxy.utils;

import exceptions.NoSuchRMIServiceException;
import exceptions.NoSuchServiceException;
import exceptions.WrongRMIServiceGenericType;
import md.ddbms.proxy.rmi.services.IProxyRMIService;
import md.ddbms.proxy.rmi.services.ProxyRMIService;
import models.port_settings.DatawarehousePortSettings;
import models.port_settings.ServerPortSettings;
import models.port_settings.ServicePortSettings;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import services.interfaces.Service;
import utils.ServiceNameToServiceMap;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class RMIServiceHelper implements IRMIServiceHelper {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<IProxyRMIService> primaryProxyRMIServices = new ArrayList<>();
    private final List<IProxyRMIService> secondaryProxyRMIServices = new ArrayList<>();

    public RMIServiceHelper(ServerPortSettings serverPortSettings)
            throws NoSuchServiceException, WrongRMIServiceGenericType {
        for (DatawarehousePortSettings datawarehousePortSettings : serverPortSettings.getDatawarehousePortSettings()) {
            ProxyRMIService proxyRMIService = new ProxyRMIService();
            for (ServicePortSettings servicePortSetting : datawarehousePortSettings.getServicePortSettings()) {
                proxyRMIService.createRMIService(
                        ServiceNameToServiceMap.getServiceClassByName(servicePortSetting.getServiceName()),
                        servicePortSetting.getServicePort());
            }
            if (datawarehousePortSettings.isPrimary()) {
                primaryProxyRMIServices.add(proxyRMIService);
            }
            else {
                secondaryProxyRMIServices.add(proxyRMIService);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Service> T getReadService(Class<T> serviceClass) throws NoSuchRMIServiceException {
        // todo load balancing
        if (secondaryProxyRMIServices.size() > 0) {
            return (T) secondaryProxyRMIServices.get(0).getRMIService(serviceClass).getObject();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Service> T getWriteService(Class<T> serviceClass) throws NoSuchRMIServiceException {
        // todo load balancing
        if (primaryProxyRMIServices.size() > 0) {
            return (T) primaryProxyRMIServices.get(0).getRMIService(serviceClass).getObject();
        }
        return null;
    }
}
