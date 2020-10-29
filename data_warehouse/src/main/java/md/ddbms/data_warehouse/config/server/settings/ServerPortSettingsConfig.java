package md.ddbms.data_warehouse.config.server.settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import config.server.settings.IServerPortSettingsConfig;
import models.port_settings.ServerPortSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import utils.PortSettingsReader;

import java.io.FileNotFoundException;

// Clone of {md.ddbms.proxy.config.server_properties class ServerPortSettingsConfig}
// cause impossibility of inter-module Bean Configuration
@Configuration
public class ServerPortSettingsConfig implements IServerPortSettingsConfig {
    @Bean
    @Scope("singleton")
    public ServerPortSettings serverPortSettings() throws FileNotFoundException, JsonProcessingException {
        return PortSettingsReader.getPortSettings();
    }
}
