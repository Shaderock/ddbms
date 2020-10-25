package md.ddbms.data_warehouse.services.interfaces;

import md.ddbms.data_warehouse.dtos.UserDTO;
import md.ddbms.data_warehouse.exceptions.FriendAlreadyAddedException;
import md.ddbms.data_warehouse.exceptions.FriendDoesNotExistException;
import md.ddbms.data_warehouse.exceptions.LoginAlreadyExistsException;
import md.ddbms.data_warehouse.exceptions.UserNotFoundException;
import md.ddbms.data_warehouse.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    User create(UserDTO userDTO) throws LoginAlreadyExistsException;

    User update(User user, UserDTO userDTO);

    User getById(int id) throws UserNotFoundException;

    User getByLogin(String login) throws UserNotFoundException;

    List<User> search(String searchQuery);

    List<User> getAllFriends(User user);

    void addFriend(User user, int friendId) throws UserNotFoundException, FriendAlreadyAddedException;

    void removeFriend(User user, int friendId) throws UserNotFoundException, FriendDoesNotExistException;

    List<User> searchFriend(User user, String searchQuery);

    List<User> getAll();
}
