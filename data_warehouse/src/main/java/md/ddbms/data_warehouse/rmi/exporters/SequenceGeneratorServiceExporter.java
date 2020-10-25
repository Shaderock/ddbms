package md.ddbms.data_warehouse.rmi.exporters;

import md.ddbms.data_warehouse.utils.DataWarehouseContext;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;
import services.RMIServiceType;
import services.interfaces.ISequenceGeneratorService;

@Component
public class SequenceGeneratorServiceExporter extends RmiServiceExporter {
    public SequenceGeneratorServiceExporter(ISequenceGeneratorService sequenceGeneratorService) {
        setServiceInterface(ISequenceGeneratorService.class);
        setService(sequenceGeneratorService);
        setServiceName(ISequenceGeneratorService.class.getSimpleName());
        setRegistryPort(DataWarehouseContext.serverPort + RMIServiceType.SEQUENCE_GENERATOR.getPortIncrement());
    }
}
