package md.ddbms.bestsn.config;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {
    public static final String SIGN_UP_URL = "/register";
    public static final String KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeTh";
    public static final String HEADER_NAME = "Authorization";
    public static final Long EXPIRATION_TIME =
            TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS);
}
