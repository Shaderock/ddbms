package md.ddbms.rmi.models.port_settings;

import md.ddbms.rmi.exceptions.NoSuchServiceException;
import lombok.Data;

import java.util.List;

@Data
public class DataWarehousePortSettings {
    private List<ServicePortSettings> servicePortSettings;
    private int mongoDBPort;
    private int ownSpringPort;

    public ServicePortSettings getServicePortSettingsByServiceName(String serviceName)
            throws NoSuchServiceException {
        for (ServicePortSettings servicePortSetting : servicePortSettings) {
            if (servicePortSetting.getServiceName().equals(serviceName)) {
                return servicePortSetting;
            }
        }
        throw new NoSuchServiceException("");
    }
}
