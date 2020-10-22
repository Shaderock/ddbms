package md.ddbms.bestsn.services;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.dtos.UserDTO;
import md.ddbms.bestsn.exceptions.FriendAlreadyAddedException;
import md.ddbms.bestsn.exceptions.FriendDoesNotExistException;
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
    private final ISequenceGeneratorService sequenceGeneratorService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(login);
        }
        return user.get();
    }

    @Override
    public User create(UserDTO userDTO) throws LoginAlreadyExistsException {

        Optional<User> user = userRepository.findByLogin(userDTO.getLogin());
        if (user.isPresent()) {
            throw new LoginAlreadyExistsException("Login " + userDTO.getLogin() + " already exists");
        }
        User createdUser = userDTO.toUser();
        createdUser.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        userRepository.save(createdUser);

        return createdUser;
    }

    @Override
    public User update(UserDTO userDTO) throws UserNotFoundException {
        return null;
    }

    @Override
    public User getById(int id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + id + " not found"));
    }

    @Override
    public User getByLogin(String login) throws UserNotFoundException {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("User with login = '" + login + "' not found"));
    }

    @Override
    public List<User> getAllFriends(User user) {
        return userRepository.findByIdIn(user.getFriendIds());
    }

    public void addFriend(User user, int friendId) throws UserNotFoundException, FriendAlreadyAddedException {
        getById(friendId);

        if (!user.getFriendIds().contains(friendId)) {
            user.getFriendIds().add(friendId);
            userRepository.save(user);
        } else {
            throw new FriendAlreadyAddedException("Friend with id = " + friendId + " already is in your friend list");
        }
    }

    @Override
    public void removeFriend(User user, int friendId) throws UserNotFoundException, FriendDoesNotExistException {
        if (user.getFriendIds().contains(friendId)) {
            user.getFriendIds().remove((Integer) friendId);
            userRepository.save(user);
        } else {
            throw new FriendDoesNotExistException("Friend with id = " + friendId + " is not found in your friend list");
        }

        getById(friendId);
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
