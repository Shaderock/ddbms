package md.ddbms.rmi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseSequence {
    @Id
    private String id;

    private int sequence;

    public void IncrementSequence() {
        sequence++;
    }
}
