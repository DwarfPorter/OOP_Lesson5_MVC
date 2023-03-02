package personal.controllers;

import personal.model.Repository;
import personal.model.User;
import personal.model.UserValidator;
import personal.model.UserValidatorImpl;

import java.util.List;

public class UserController {
    private final Repository repository;
    private UserValidator validator = new UserValidatorImpl();

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {
        validator.nameValidation(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        for (User user : repository.getAllUsers()) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public List<User> readAllUsers() {
        return repository.getAllUsers();
    }

    public boolean deleteUser(String id) {
        return repository.deleteUser(id);
    }

    public boolean updateUser(String id, User user) {
        return repository.updateUser(id, user);
    }
    public boolean hasUpdateUser(String id){
        return repository.hasUpdateUser(id);
    }

    public void saveMode(String delimiter){
        repository.setDelimiter(delimiter);
    }
}
