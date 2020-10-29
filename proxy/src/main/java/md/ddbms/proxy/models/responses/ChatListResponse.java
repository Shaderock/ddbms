package md.ddbms.proxy.models.responses;

import lombok.Data;
import md.ddbms.rmi.models.MessageHistory;
import md.ddbms.rmi.models.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ChatListResponse {
    private List<Integer> withUserIds;

    public ChatListResponse(List<MessageHistory> messageHistories, User user) {
        Set<Integer> withUserIdsSet = new HashSet<>();
        for (MessageHistory messageHistory : messageHistories) {
            withUserIdsSet.addAll(messageHistory.getUsersId());
        }
        withUserIdsSet.remove(user.getId());
        withUserIds = new ArrayList<>(withUserIdsSet);
    }
}
