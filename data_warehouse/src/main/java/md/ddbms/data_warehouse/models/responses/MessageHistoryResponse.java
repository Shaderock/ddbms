package md.ddbms.data_warehouse.models.responses;

import lombok.Data;
import md.ddbms.data_warehouse.models.Message;
import md.ddbms.data_warehouse.models.MessageHistory;

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
