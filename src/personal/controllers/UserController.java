package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.Iterator;
import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
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

    public List<User> readAllUsers() {
        return repository.getAllUsers();
    }

    public boolean deleteUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(userId)) {
                iterator.remove();
                repository.refreshRepository(users);
                return true;
            }
        }
        throw new Exception("User not found");
    }

    public boolean updateUser(String userId, String newInfo, int operation) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                switch (operation) {
                    case 1:
                        user.setFirstName(newInfo);
                        repository.refreshRepository(users);
                        break;
                    case 2:
                        user.setLastName(newInfo);
                        repository.refreshRepository(users);
                        break;
                    case 3:
                        user.setPhone(newInfo);
                        repository.refreshRepository(users);
                        break;
                }
                return true;
            }
        }
        throw new Exception("User not found");
    }


}
