package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    String CreateUser(User user);
    boolean deleteUser(String id);
    boolean hasUpdateUser(String id);
    boolean updateUser(String id, User user);
    void setDelimiter(String delimiter);
}
