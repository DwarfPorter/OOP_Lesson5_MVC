package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }

    public void saveUser(User user) {
        repository.CreateUser(user);
    }

    public void deleteUser(String id) {
        repository.deleteUser(id);
    }

    public void updateUser(String id) {
        repository.updateUser(id);
    }

    public void getAllUsers() {
        System.out.println(repository.getAllUsers());
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }
}
