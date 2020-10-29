package md.ddbms.data_warehouse.rmi.exporters;

import md.ddbms.rmi.exceptions.NoSuchServiceException;
import md.ddbms.data_warehouse.utils.DataWarehouseContext;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;
import md.ddbms.rmi.interfaces.IMessageService;

@Component
public class MessageServiceExporter extends RmiServiceExporter {
    public MessageServiceExporter(IMessageService messageService) throws NoSuchServiceException {
        setServiceInterface(IMessageService.class);
        setService(messageService);
        setServiceName(IMessageService.class.getSimpleName());
        setRegistryPort(DataWarehouseContext.datawarehousePortSettings
                .getServicePortSettingsByServiceName(IMessageService.class.getSimpleName()).getServicePort());
    }
}
