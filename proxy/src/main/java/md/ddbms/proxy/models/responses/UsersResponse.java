package md.ddbms.proxy.models.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import md.ddbms.rmi.models.User;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UsersResponse {
    private ArrayList<UserResponse> users = new ArrayList<>();

    public UsersResponse(List<User> users) {
        for (User user : users) {
            addUser(new UserResponse(user));
        }
    }

    public void addUser(UserResponse userResponse) {
        users.add(userResponse);
    }
}
