package config.server.settings;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.port_settings.ServerPortSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.io.FileNotFoundException;

public interface IServerPortSettingsConfig {
    @Bean
    @Scope("singleton")
    ServerPortSettings serverPortSettings() throws FileNotFoundException, JsonProcessingException;
}
