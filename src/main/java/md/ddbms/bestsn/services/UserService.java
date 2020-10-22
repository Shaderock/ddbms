package md.ddbms.bestsn.services;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.dtos.UserDTO;
import md.ddbms.bestsn.exceptions.LoginAlreadyExistsException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.User;
import md.ddbms.bestsn.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByLogin(login);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(login);
        }
        return user.get();
    }

    @Override
    public User create(UserDTO userDTO) throws LoginAlreadyExistsException {

        Optional<User> user = userRepository.findUserByLogin(userDTO.getLogin());
        if (user.isPresent()) {
            throw new LoginAlreadyExistsException("Login " + userDTO.getLogin() + " already exists");
        }

        User createdUser = userDTO.toUser();
        userRepository.save(createdUser);

        return createdUser;
    }

    @Override
    public User update(UserDTO userDTO) throws UserNotFoundException {
        return null;
    }

    @Override
    public User getById(int id) throws UserNotFoundException {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + id + " not found"));
    }

    @Override
    public User getByLogin(String login) throws UserNotFoundException {
        return userRepository.findUserByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("User with login = '" + login + "' not found"));
    }

    @Override
    @Transactional
    public void addFriend(User user, int friendId) throws UserNotFoundException {
        User friend = userRepository.findUserById(friendId)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + friendId + " not found"));
        user.getFriendList().add(friend);
        friend.getRequestedFriendList().add(user);
    }

    @Override
    public List<User> getAll() {
        return null;
    }


}
