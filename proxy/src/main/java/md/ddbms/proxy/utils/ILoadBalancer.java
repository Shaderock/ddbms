package md.ddbms.proxy.utils;

import org.springframework.stereotype.Component;

@Component
public interface ILoadBalancer {
    int getDataWarehousePort();
}
