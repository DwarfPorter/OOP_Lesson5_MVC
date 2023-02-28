package personal.controllers;

import personal.model.Repository;
import personal.model.User;
import personal.model.ValidateUser;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public ValidateUser validator = new ValidateUser();


    public void saveUser(User user) throws Exception {
        validator.check(user);
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

    public List<User> readUsers(){
        List<User> users = repository.getAllUsers();
        return users;
    }

    public void deleteUser(String usrId){
        repository.deleteUser(usrId);
    }

    public void updateUser(String id, String firstName, String lastName, String phone){
        repository.updateUser(id, firstName, lastName, phone);
    }
}
