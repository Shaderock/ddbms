package md.ddbms.rmi.config.settings.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import md.ddbms.rmi.models.port_settings.ServerPortSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import md.ddbms.rmi.utils.PortSettingsReader;

import java.io.FileNotFoundException;

@Configuration
public class ServerPortSettingsConfig{
    @Bean
    @Scope("singleton")
    public ServerPortSettings serverPortSettings() throws FileNotFoundException, JsonProcessingException {
        return PortSettingsReader.getPortSettings();
    }
}
