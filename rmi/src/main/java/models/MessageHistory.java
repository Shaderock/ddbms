package models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MessageHistory implements Serializable {
    @Transient
    public static final String SEQUENCE_NAME = "messageHistorySequence";

    @Id
    @EqualsAndHashCode.Include
    private int id;

    @EqualsAndHashCode.Include
    @ElementCollection
    private Set<Integer> usersId = new HashSet<>();

    @SuppressWarnings("JpaAttributeTypeInspection")
    private List<Message> messages = new ArrayList<>();

    public MessageHistory(int id, User user1, User user2) {
        this.id = id;
        usersId.add(user1.getId());
        usersId.add(user2.getId());
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
