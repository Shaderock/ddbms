package md.ddbms.proxy.caching.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.ddbms.rmi.models.MessageHistory;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "cached message history", timeToLive = 600)
public class CachedMessageHistory {
    @Id
    private String id;

    private MessageHistory messageHistory;
}
