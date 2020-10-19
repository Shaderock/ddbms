package md.ddbms.bestsn.services;

import lombok.RequiredArgsConstructor;
import md.ddbms.bestsn.dtos.UserDTO;
import md.ddbms.bestsn.exceptions.LoginAlreadyExistsException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.User;
import md.ddbms.bestsn.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User create(UserDTO userDTO) throws LoginAlreadyExistsException {
        userRepository.findUserByLogin(userDTO.getLogin())
                .orElseThrow(() -> new LoginAlreadyExistsException(
                        "Login " + userDTO.getLogin() + " already exists"
                ));
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
        return null;
    }

    @Override
    public User getByLogin(String login) throws UserNotFoundException {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
