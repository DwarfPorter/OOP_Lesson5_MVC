package personal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    private void RepositorySave(List<User> users){
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        RepositorySave(users);
        return user.getId();
    }

    @Override
    public void UpdateUser(String id, String firstName, String lastName, String phone) {
        List<User> users = getAllUsers();
        try {
            for (User item: users) {
                if (Objects.equals(id, item.getId())) {
                    item.setFirstName(firstName);
                    item.setLastName(lastName);
                    item.setPhone(phone);
                    break;
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        RepositorySave(users);
    }

    @Override
    public void DeleteUser(String id) {
        List<User> users = getAllUsers();
        try {
            for (User item: users) {
                if (Objects.equals(id, item.getId())) {
                    users.remove(item);
                    break;
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        RepositorySave(users);
    }
}
