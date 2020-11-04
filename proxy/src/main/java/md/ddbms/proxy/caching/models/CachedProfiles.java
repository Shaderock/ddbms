package md.ddbms.proxy.caching.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ddbms.proxy.models.responses.UsersResponse;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "cached profiles", timeToLive = 300)
public class CachedProfiles {
    @Id
    private String id;

    private UsersResponse usersResponse;
}
