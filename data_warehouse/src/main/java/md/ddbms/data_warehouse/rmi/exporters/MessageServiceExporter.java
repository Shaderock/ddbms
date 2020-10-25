package md.ddbms.data_warehouse.rmi.exporters;

import md.ddbms.data_warehouse.utils.DataWarehouseContext;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;
import services.RMIServiceType;
import services.interfaces.IMessageService;

@Component
public class MessageServiceExporter extends RmiServiceExporter {
    public MessageServiceExporter(IMessageService messageService) {
        setServiceInterface(IMessageService.class);
        setService(messageService);
        setServiceName(IMessageService.class.getSimpleName());
        setRegistryPort(DataWarehouseContext.serverPort + RMIServiceType.MESSAGE.getPortIncrement());
    }
}
