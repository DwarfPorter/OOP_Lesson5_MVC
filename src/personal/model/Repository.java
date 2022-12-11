package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    String CreateUser(User user);

    void UpdateUser(String id, String firstName, String lastName, String phone);

    void DeleteUser(String id);
}
