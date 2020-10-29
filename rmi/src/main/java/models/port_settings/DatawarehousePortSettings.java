package models.port_settings;

import exceptions.NoSuchServiceException;
import lombok.Data;

import java.util.List;

@Data
public class DatawarehousePortSettings {
    private List<ServicePortSettings> servicePortSettings;
    private int mongoDBPort;
    private int ownSpringPort;
    private boolean primary;

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
