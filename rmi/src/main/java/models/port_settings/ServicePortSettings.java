package models.port_settings;

import lombok.Data;

@Data
public class ServicePortSettings {
    private String serviceName;
    private int servicePort;
}
