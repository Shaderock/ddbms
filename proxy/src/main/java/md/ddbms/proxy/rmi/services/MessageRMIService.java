package md.ddbms.proxy.rmi.services;

import md.ddbms.proxy.utils.ILoadBalancer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import services.RMIServiceType;
import services.interfaces.IMessageService;

@Service
public class MessageRMIService extends RMIService {
    public MessageRMIService(ILoadBalancer loadBalancer) {
        super(loadBalancer.getDataWarehousePort(), RMIServiceType.MESSAGE, IMessageService.class);
    }
}
