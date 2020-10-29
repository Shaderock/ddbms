package md.ddbms.data_warehouse.rmi.exporters;

import md.ddbms.rmi.exceptions.NoSuchServiceException;
import md.ddbms.data_warehouse.utils.DataWarehouseContext;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;
import md.ddbms.rmi.interfaces.ISequenceGeneratorService;

@Component
public class SequenceGeneratorServiceExporter extends RmiServiceExporter {
    public SequenceGeneratorServiceExporter(ISequenceGeneratorService sequenceGeneratorService)
            throws NoSuchServiceException {
        setServiceInterface(ISequenceGeneratorService.class);
        setService(sequenceGeneratorService);
        setServiceName(ISequenceGeneratorService.class.getSimpleName());
        setRegistryPort(DataWarehouseContext.datawarehousePortSettings
                .getServicePortSettingsByServiceName(ISequenceGeneratorService.class.getSimpleName()).getServicePort());
    }
}
