package md.ddbms.rmi.utils;

import md.ddbms.rmi.exceptions.NoSuchServiceException;
import org.reflections.Reflections;
import md.ddbms.rmi.interfaces.Service;

import java.util.HashMap;
import java.util.Map;

public class ServiceNameToServiceMap {
    private static final Map<String, Class<? extends Service>> STRING_CLASS_MAP =
            new HashMap<String, Class<? extends Service>>() {{
                for (Class<? extends Service> serviceSubclass :
                        (new Reflections("md.ddbms.rmi.interfaces")).getSubTypesOf(Service.class)) {
                    put(serviceSubclass.getSimpleName(), serviceSubclass);
                }
            }};

    public static Class<? extends Service> getServiceClassByName(String serviceName) throws NoSuchServiceException {
        Class<? extends Service> serviceClass = STRING_CLASS_MAP.get(serviceName);
        if (serviceClass == null) {
            throw new NoSuchServiceException("The service of class = '"
                    + serviceName + "' was not found in list of RMI md.ddbms.rmi.services");
        }
        return serviceClass;
    }
}
