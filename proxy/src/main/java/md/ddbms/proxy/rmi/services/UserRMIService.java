package md.ddbms.proxy.rmi.services;

import md.ddbms.proxy.utils.ILoadBalancer;
import org.springframework.stereotype.Service;
import services.RMIServiceType;
import services.interfaces.IUserService;

@Service
public class UserRMIService extends RMIService {
    public UserRMIService(ILoadBalancer loadBalancer) {
        super(loadBalancer.getDataWarehousePort(), RMIServiceType.USER, IUserService.class);
    }
}
