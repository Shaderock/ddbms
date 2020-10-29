package md.ddbms.data_warehouse.config.server.properties;

import lombok.RequiredArgsConstructor;
import md.ddbms.data_warehouse.utils.DataWarehouseContext;
import models.port_settings.ServerPortSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerPortCustomizer
        implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    private final ServerPortSettings serverPortSettings;

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        DataWarehouseContext.setup(serverPortSettings);
        factory.setPort(DataWarehouseContext.datawarehousePortSettings.getOwnSpringPort());
    }
}
