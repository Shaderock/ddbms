package md.ddbms.proxy.caching.services.proxies;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import md.ddbms.proxy.rmi.services.IRMIServiceHelper;
import md.ddbms.proxy.rmi.services.IRMIServiceStorage;
import md.ddbms.proxy.rmi.services.RMIServiceHelper;
import md.ddbms.rmi.dtos.UserDTO;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.rmi.interfaces.IUserService;
import md.ddbms.rmi.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceProxy implements IUserService {
    private final IRMIServiceStorage rmiServiceStorage;

    @Override
    public User create(UserDTO userDTO)
            throws LoginAlreadyExistsException, NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        User user = rmiServiceHelper.getService(IUserService.class).create(userDTO);
        rmiServiceHelper.release();
        return user;
    }

    @Override
    public User update(User user, UserDTO userDTO) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        User updatedUser = rmiServiceHelper.getService(IUserService.class).update(user, userDTO);
        rmiServiceHelper.release();
        return updatedUser;
    }

    @Override
    public User getById(int id) throws UserNotFoundException, NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        User user = rmiServiceHelper.getService(IUserService.class).getById(id);
        rmiServiceHelper.release();
        return user;
    }

    @Override
    public User getByLogin(String login)
            throws UserNotFoundException, NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        User user = rmiServiceHelper.getService(IUserService.class).getByLogin(login);
        rmiServiceHelper.release();
        return user;
    }

    @Override
    public List<User> search(String searchQuery) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        List<User> users = rmiServiceHelper.getService(IUserService.class).search(searchQuery);
        rmiServiceHelper.release();
        return users;
    }

    @Override
    public List<User> getAllFriends(User user) throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        List<User> users = rmiServiceHelper.getService(IUserService.class).getAllFriends(user);
        rmiServiceHelper.release();
        return users;
    }

    @Override
    public void addFriend(User user, int friendId)
            throws UserNotFoundException, FriendAlreadyAddedException,
            NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        rmiServiceHelper.getService(IUserService.class).addFriend(user, friendId);
        rmiServiceHelper.release();
    }

    @Override
    public void removeFriend(User user, int friendId)
            throws UserNotFoundException, FriendDoesNotExistException,
            NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        rmiServiceHelper.getService(IUserService.class).removeFriend(user, friendId);
        rmiServiceHelper.release();
    }

    @Override
    public List<User> searchFriend(User user, String searchQuery)
            throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        List<User> users = rmiServiceHelper.getService(IUserService.class).searchFriend(user, searchQuery);
        rmiServiceHelper.release();
        return users;
    }

    @Override
    public List<User> getAll() throws NoSuchRMIServiceException, ProxyRMIServiceNotFound {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        List<User> users = rmiServiceHelper.getService(IUserService.class).getAll();
        rmiServiceHelper.release();
        return users;
    }

    @SneakyThrows({NoSuchRMIServiceException.class, ProxyRMIServiceNotFound.class})
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IRMIServiceHelper rmiServiceHelper = new RMIServiceHelper(rmiServiceStorage);
        UserDetails userDetails = rmiServiceHelper.getService(IUserService.class).loadUserByUsername(username);
        rmiServiceHelper.release();
        return userDetails;
    }
}
