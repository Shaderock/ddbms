package models.port_settings;

import lombok.Data;

import java.util.List;

@Data
public class ServerPortSettings {
    private int proxyPort;
    private List<DatawarehousePortSettings> datawarehousePortSettings;
}
