package md.ddbms.proxy.models.responses;

import lombok.Data;
import md.ddbms.rmi.models.Message;
import md.ddbms.rmi.models.MessageHistory;

import java.util.List;

@Data
public class MessageHistoryResponse {
    private int withUserId;

    private List<Message> messages;

    public MessageHistoryResponse(MessageHistory messageHistory, int withUserId) {
        this.withUserId = withUserId;
        this.messages = messageHistory.getMessages();
    }
}
