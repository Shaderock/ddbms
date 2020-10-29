package md.ddbms.proxy.services.proxies;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import md.ddbms.proxy.utils.IRMIServiceHelper;
import md.ddbms.rmi.dtos.UserDTO;
import md.ddbms.rmi.exceptions.*;
import md.ddbms.rmi.interfaces.IUserService;
import md.ddbms.rmi.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceProxy implements IUserService {
    private final IRMIServiceHelper rmiServiceHelper;

    @Override
    public User create(UserDTO userDTO) throws LoginAlreadyExistsException, NoSuchRMIServiceException {
        return rmiServiceHelper.getWriteService(IUserService.class).create(userDTO);
    }

    @Override
    public User update(User user, UserDTO userDTO) throws NoSuchRMIServiceException {
        return rmiServiceHelper.getWriteService(IUserService.class).update(user, userDTO);
    }

    @Override
    public User getById(int id) throws UserNotFoundException, NoSuchRMIServiceException {
        return rmiServiceHelper.getReadService(IUserService.class).getById(id);
    }

    @Override
    public User getByLogin(String login) throws UserNotFoundException, NoSuchRMIServiceException {
        return rmiServiceHelper.getReadService(IUserService.class).getByLogin(login);
    }

    @Override
    public List<User> search(String searchQuery) throws NoSuchRMIServiceException {
        return rmiServiceHelper.getReadService(IUserService.class).search(searchQuery);
    }

    @Override
    public List<User> getAllFriends(User user) throws NoSuchRMIServiceException {
        return rmiServiceHelper.getReadService(IUserService.class).getAllFriends(user);
    }

    @Override
    public void addFriend(User user, int friendId)
            throws UserNotFoundException, FriendAlreadyAddedException, NoSuchRMIServiceException {
        rmiServiceHelper.getWriteService(IUserService.class).addFriend(user, friendId);
    }

    @Override
    public void removeFriend(User user, int friendId) throws UserNotFoundException, FriendDoesNotExistException, NoSuchRMIServiceException {
        rmiServiceHelper.getWriteService(IUserService.class).removeFriend(user, friendId);
    }

    @Override
    public List<User> searchFriend(User user, String searchQuery) throws NoSuchRMIServiceException {
        return rmiServiceHelper.getReadService(IUserService.class).searchFriend(user, searchQuery);
    }

    @Override
    public List<User> getAll() throws NoSuchRMIServiceException {
        return rmiServiceHelper.getReadService(IUserService.class).getAll();
    }

    @SneakyThrows(NoSuchRMIServiceException.class)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return rmiServiceHelper.getReadService(IUserService.class).loadUserByUsername(username);
    }
}
