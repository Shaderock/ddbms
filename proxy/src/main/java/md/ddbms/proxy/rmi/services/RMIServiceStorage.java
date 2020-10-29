package md.ddbms.proxy.rmi.services;

import md.ddbms.rmi.exceptions.NoSuchServiceException;
import md.ddbms.rmi.exceptions.ProxyRMIServiceNotFound;
import md.ddbms.rmi.exceptions.WrongRMIServiceGenericType;
import md.ddbms.rmi.interfaces.Service;
import md.ddbms.rmi.models.port_settings.DataWarehousePortSettings;
import md.ddbms.rmi.models.port_settings.ServerPortSettings;
import md.ddbms.rmi.models.port_settings.ServicePortSettings;
import md.ddbms.rmi.utils.ServiceNameToServiceMap;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope("singleton")
public class RMIServiceStorage implements IRMIServiceStorage {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<IProxyRMIService> proxyRMIServices = new ArrayList<>();

    private final List<Integer> proxyConnections = Collections.synchronizedList(new ArrayList<>());

    public RMIServiceStorage(ServerPortSettings serverPortSettings)
            throws NoSuchServiceException, WrongRMIServiceGenericType {
        for (DataWarehousePortSettings datawarehousePortSettings : serverPortSettings.getDatawarehousePortSettings()) {
            ProxyRMIService proxyRMIService = new ProxyRMIService();
            for (ServicePortSettings servicePortSetting : datawarehousePortSettings.getServicePortSettings()) {
                proxyRMIService.createRMIService(
                        ServiceNameToServiceMap.getServiceClassByName(servicePortSetting.getServiceName()),
                        servicePortSetting.getServicePort());
            }
            proxyRMIServices.add(proxyRMIService);

            proxyConnections.add(0);
        }
    }

    @Override
    public <T extends Service> IProxyRMIService getService()
            throws ProxyRMIServiceNotFound {
        if (proxyRMIServices.size() == 0) {
            throw new ProxyRMIServiceNotFound("There is no rmiService set up.");
        }
        return getLeastConnectionService();
    }

    private synchronized IProxyRMIService getLeastConnectionService() {
        int index = proxyConnections.indexOf(Collections.min(proxyConnections));
        proxyConnections.set(index, proxyConnections.get(index) + 1);
        return proxyRMIServices.get(index);
    }

    public synchronized void reduceConnection(IProxyRMIService proxyRMIService) {
        proxyConnections.set(proxyRMIServices.indexOf(proxyRMIService),
                proxyConnections.get(proxyRMIServices.indexOf(proxyRMIService)) - 1);
    }
}
