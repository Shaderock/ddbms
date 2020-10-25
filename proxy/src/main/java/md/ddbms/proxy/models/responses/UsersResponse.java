package md.ddbms.proxy.models.responses;

import lombok.Data;
import models.User;

import java.util.ArrayList;
import java.util.List;

@Data
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
