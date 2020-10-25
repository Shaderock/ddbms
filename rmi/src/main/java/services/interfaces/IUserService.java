package services.interfaces;


import dtos.UserDTO;
import exceptions.FriendAlreadyAddedException;
import exceptions.FriendDoesNotExistException;
import exceptions.LoginAlreadyExistsException;
import exceptions.UserNotFoundException;
import models.User;
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
