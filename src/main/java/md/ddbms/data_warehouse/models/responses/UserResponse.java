package md.ddbms.data_warehouse.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import md.ddbms.data_warehouse.models.User;

@Data
@AllArgsConstructor
public class UserResponse {
    private final int id;
    private final String login;
    private final String firstName;
    private final String lastName;

    public UserResponse(User user) {
        id = user.getId();
        login = user.getLogin();
        firstName = user.getFirstName();
        lastName = user.getLastName();
    }
}
