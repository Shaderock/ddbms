package md.ddbms.proxy.rmi.services;

import md.ddbms.rmi.exceptions.WrongRMIServiceGenericType;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import md.ddbms.rmi.interfaces.Service;

// T should be extended from md.ddbms.rmi.interfaces.Service
public class RMIService <T> extends RmiProxyFactoryBean {

    public RMIService(int dataWarehousePort, Class<T> typeClass)
            throws WrongRMIServiceGenericType {
        if (! Service.class.isAssignableFrom(typeClass)) {
            throw new WrongRMIServiceGenericType("Can't create RMIService for nonService class (" +
                    typeClass.getSimpleName() + ")");
        }

        this.setServiceUrl("rmi://localhost:" + dataWarehousePort + "/" + typeClass.getSimpleName());
        this.setServiceInterface(typeClass);
    }
}
