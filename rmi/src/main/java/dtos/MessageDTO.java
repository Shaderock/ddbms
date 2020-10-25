package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Message;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 2L;

    private String text;

    public Message toMessage(boolean straightDirection) {
        Message message = new Message(this.text, straightDirection);
        message.setDateTime();
        return message;
    }
}
