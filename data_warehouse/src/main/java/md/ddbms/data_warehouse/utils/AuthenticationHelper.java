package md.ddbms.data_warehouse.utils;

import md.ddbms.data_warehouse.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationHelper {
    public static User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
