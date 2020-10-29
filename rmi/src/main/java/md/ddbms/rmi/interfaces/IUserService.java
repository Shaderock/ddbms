package md.ddbms.rmi.interfaces;


import md.ddbms.rmi.dtos.UserDTO;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.rmi.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService, Service {
    User create(UserDTO userDTO) throws LoginAlreadyExistsException, NoSuchRMIServiceException;

    User update(User user, UserDTO userDTO) throws NoSuchRMIServiceException;

    User getById(int id) throws UserNotFoundException, NoSuchRMIServiceException;

    User getByLogin(String login) throws UserNotFoundException, NoSuchRMIServiceException;

    List<User> search(String searchQuery) throws NoSuchRMIServiceException;

    List<User> getAllFriends(User user) throws NoSuchRMIServiceException;

    void addFriend(User user, int friendId) throws UserNotFoundException, FriendAlreadyAddedException, NoSuchRMIServiceException;

    void removeFriend(User user, int friendId) throws UserNotFoundException, FriendDoesNotExistException, NoSuchRMIServiceException;

    List<User> searchFriend(User user, String searchQuery) throws NoSuchRMIServiceException;

    List<User> getAll() throws NoSuchRMIServiceException;
}
