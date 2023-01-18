package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {
        validateUser(user);
        repository.CreateUser(user);
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

    public List<User> readList() {
        List<User> result = repository.getAllUsers();
        return result;
    }

    public void updateUser(String idNumber, User newGuy) throws Exception {
        idPresenceValidation(idNumber);
        newGuy.setId(idNumber);
        validateUserId(newGuy);
        repository.updateUser(newGuy);
    }

    private void validateUser(User user) throws Exception {

        if (user.getFirstName().contains(" "))
            throw new Exception("User name has unacceptable characters");
        if (user.getLastName().contains(" "))
            throw new Exception("User name has unacceptable characters");
    }
    private void validateUserId (User user) throws Exception {

        if (user.getId().isEmpty())
            throw new Exception("User not found");
        validateUser(user);
    }
    public void idPresenceValidation (String inutId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User n: users){
            if(n.getId().equals(inutId))
                return;
        }
        throw new Exception("No such ID here");
    }
}
