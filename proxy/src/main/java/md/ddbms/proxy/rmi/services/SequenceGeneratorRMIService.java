package md.ddbms.proxy.rmi.services;

import md.ddbms.proxy.utils.ILoadBalancer;
import org.springframework.stereotype.Service;
import services.RMIServiceType;
import services.interfaces.ISequenceGeneratorService;

@Service
public class SequenceGeneratorRMIService extends RMIService {
    public SequenceGeneratorRMIService(ILoadBalancer loadBalancer) {
        super(loadBalancer.getDataWarehousePort(),
                RMIServiceType.SEQUENCE_GENERATOR,
                ISequenceGeneratorService.class);
    }
}
