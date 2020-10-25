package md.ddbms.proxy.rmi.services;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import services.RMIServiceType;

public class RMIService extends RmiProxyFactoryBean {
    public RMIService(int dataWarehousePort, RMIServiceType RMIServiceType, Class<?> serviceInterface) {
        // todo remove
        System.out.println("creating rmi service: " + serviceInterface.getSimpleName());
        this.setServiceUrl("rmi://localhost:" + (dataWarehousePort +
                RMIServiceType.getPortIncrement()) + "/" + serviceInterface.getSimpleName());
        this.setServiceInterface(serviceInterface);
    }
}
