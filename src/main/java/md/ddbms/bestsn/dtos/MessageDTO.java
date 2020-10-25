package md.ddbms.bestsn.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.ddbms.bestsn.models.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String text;

    public Message toMessage(boolean straightDirection) {
        Message message = new Message(this.text, straightDirection);
        message.setDateTime();
        return message;
    }
}
