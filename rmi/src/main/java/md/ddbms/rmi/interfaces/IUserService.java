package md.ddbms.rmi.interfaces;


import md.ddbms.rmi.dtos.UserDTO;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.rmi.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService, Service {
    User create(UserDTO userDTO) throws LoginAlreadyExistsException, NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    User update(User user, UserDTO userDTO) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    User getById(int id) throws UserNotFoundException, NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    User getByLogin(String login) throws UserNotFoundException, NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    List<User> search(String searchQuery) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    List<User> getAllFriends(User user) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    void addFriend(User user, int friendId) throws UserNotFoundException, FriendAlreadyAddedException, NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    void removeFriend(User user, int friendId) throws UserNotFoundException, FriendDoesNotExistException, NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    List<User> searchFriend(User user, String searchQuery) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound;

    List<User> getAll() throws NoSuchRMIServiceException, ProxyRMIServiceNotFound;
}
