package md.ddbms.proxy.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component()
@Scope("prototype")
public class LoadBalancer implements ILoadBalancer {
    // todo
    @Override
    public int getDataWarehousePort() {
        return 50010;
    }
}
