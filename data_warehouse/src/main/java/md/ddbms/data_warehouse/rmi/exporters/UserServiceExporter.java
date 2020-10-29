package md.ddbms.data_warehouse.rmi.exporters;

import md.ddbms.rmi.exceptions.NoSuchServiceException;
import md.ddbms.data_warehouse.utils.DataWarehouseContext;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;
import md.ddbms.rmi.interfaces.IUserService;

@Component
public class UserServiceExporter extends RmiServiceExporter {
    public UserServiceExporter(IUserService userService) throws NoSuchServiceException {
        setServiceInterface(IUserService.class);
        setService(userService);
        setServiceName(IUserService.class.getSimpleName());
        setRegistryPort(DataWarehouseContext.datawarehousePortSettings
                .getServicePortSettingsByServiceName(IUserService.class.getSimpleName()).getServicePort());
    }
}
