package models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class Message {

    private String text;

    @Setter(AccessLevel.NONE)
    private LocalDateTime dateTime;

    private boolean straightDirection;

    public Message(String text, boolean straightDirection) {
        this.text = text;
        this.straightDirection = straightDirection;
    }

    public void setDateTime() {
        this.dateTime = LocalDateTime.now();
    }
}
