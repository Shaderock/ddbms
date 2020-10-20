package md.ddbms.bestsn.utils;

import md.ddbms.bestsn.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationHelper {
    public static User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
//        String username = userDetails.getUsername();
//        return userService.getByLogin(username);
    }
}
