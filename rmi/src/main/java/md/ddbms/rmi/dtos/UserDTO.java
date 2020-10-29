package md.ddbms.rmi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import md.ddbms.rmi.models.User;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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
