package md.ddbms.proxy.config.server.properties;

import lombok.RequiredArgsConstructor;
import md.ddbms.rmi.models.port_settings.ServerPortSettings;
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
        factory.setPort(serverPortSettings.getProxyPort());
    }
}
