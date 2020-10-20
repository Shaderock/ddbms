package md.ddbms.bestsn.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileResponse {
    private String login;
    private String firstName;
    private String lastName;
}
