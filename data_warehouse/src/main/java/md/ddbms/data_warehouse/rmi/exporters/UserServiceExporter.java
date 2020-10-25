package md.ddbms.data_warehouse.rmi.exporters;

import md.ddbms.data_warehouse.utils.DataWarehouseContext;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;
import services.RMIServiceType;
import services.interfaces.IUserService;

@Component
public class UserServiceExporter extends RmiServiceExporter {
    public UserServiceExporter(IUserService userService) {
        setServiceInterface(IUserService.class);
        setService(userService);
        setServiceName(IUserService.class.getSimpleName());
        setRegistryPort(DataWarehouseContext.serverPort + RMIServiceType.USER.getPortIncrement());
    }
}
