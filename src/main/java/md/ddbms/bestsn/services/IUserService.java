package md.ddbms.bestsn.services;

import md.ddbms.bestsn.dtos.UserDTO;
import md.ddbms.bestsn.exceptions.LoginAlreadyExistsException;
import md.ddbms.bestsn.exceptions.UserNotFoundException;
import md.ddbms.bestsn.models.User;

import java.util.List;

public interface IUserService {
    User create(UserDTO userDTO) throws LoginAlreadyExistsException;

    User update(UserDTO userDTO) throws UserNotFoundException;

    User getById(int id) throws UserNotFoundException;

    User getByLogin(String login) throws UserNotFoundException;

    List<User> getAll();
}
