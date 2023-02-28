package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    String CreateUser(User user);
    void deleteUser(String usrId);
    void updateUser(String id, String firstName, String lastName, String phone);
}
