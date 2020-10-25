package md.ddbms.data_warehouse.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Data
@Entity
@AllArgsConstructor
public class DatabaseSequence {
    @Id
    private String id;

    private int sequence;

    public void IncrementSequence() {
        sequence++;
    }
}
