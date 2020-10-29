package md.ddbms.proxy.config.server.proxies;

import lombok.RequiredArgsConstructor;
import md.ddbms.proxy.services.proxies.MessageServiceProxy;
import md.ddbms.proxy.services.proxies.SequenceGeneratorServiceProxy;
import md.ddbms.proxy.services.proxies.UserServiceProxy;
import md.ddbms.proxy.utils.IRMIServiceHelper;
import md.ddbms.rmi.interfaces.IMessageService;
import md.ddbms.rmi.interfaces.ISequenceGeneratorService;
import md.ddbms.rmi.interfaces.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProxyServicesConfig {
    private final IRMIServiceHelper rmiServiceHelper;

    @Bean
    public IMessageService messageService() {
        return new MessageServiceProxy(rmiServiceHelper);
    }

    @Bean
    public ISequenceGeneratorService sequenceGeneratorService() {
        return new SequenceGeneratorServiceProxy(rmiServiceHelper);
    }

    @Bean
    public IUserService userService() {
        return new UserServiceProxy(rmiServiceHelper);
    }
}
