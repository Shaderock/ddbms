package md.ddbms.data_warehouse.rmi.services;

import dtos.UserDTO;
import exceptions.*;
import lombok.RequiredArgsConstructor;
import md.ddbms.data_warehouse.repositories.UserRepository;
import models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.ISequenceGeneratorService;
import services.interfaces.IUserService;

import java.util.*;

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
    public User create(UserDTO userDTO) throws LoginAlreadyExistsException, NoSuchRMIServiceException {
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
    @Transactional
    public User update(User user, UserDTO userDTO) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return user;
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
    public List<User> search(String searchQuery) {
        Set<User> users = new HashSet<>();
        String[] subQueries = searchQuery.split(" ");
        for (String subQuery : subQueries) {
            users.addAll(userRepository
                    .findByLoginContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(subQuery, subQuery, subQuery));
        }
        return new ArrayList<>(users);
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
    public List<User> searchFriend(User user, String searchQuery) {
        Set<User> notFriendUsers = new HashSet<>();
        String[] subQueries = searchQuery.split(" ");
        for (String subQuery : subQueries) {
            notFriendUsers.addAll(userRepository
                    .findByLoginContainingOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(subQuery, subQuery, subQuery));
        }
        List<User> friendUsers = new ArrayList<>(notFriendUsers);
        for (int i = 0; i < friendUsers.size(); ++i) {
            if (!user.getFriendIds().contains(friendUsers.get(i).getId())) {
                friendUsers.remove(i);
                --i;
            }
        }
        return friendUsers;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
