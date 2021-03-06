package md.ddbms.bestsn.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import md.ddbms.bestsn.models.User;

@Data
@AllArgsConstructor
public class UserDTO {
    private String login;

    private String firstName;

    private String lastName;

    private String password;

    public User toUser() {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .login(login)
                .password(password)
                .build();
    }
}
