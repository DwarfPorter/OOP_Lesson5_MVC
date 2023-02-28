package personal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {

        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            if (!line.isEmpty()) {
                if (line.contains(",")) {
                    users.add(mapper.map(line));
                } else {
                    users.add(mapper.alternativeMap(line));
                }
            }
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {
        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        List<String> lines = new ArrayList<>();
        for (User item : users) {
            if (item.getTypeOfFormat() == 1) {
                lines.add(mapper.map(item));
            } else {
                lines.add(mapper.alternativeMap(item));
            }
        }
        fileOperation.saveAllLines(lines);
        return id;
    }

    public void refreshRepository(List<User> users) {

        List<String> lines = new ArrayList<>();
        for (User item : users) {
            if (item.getTypeOfFormat() == 1) {
                lines.add(mapper.map(item));
            } else {
                lines.add(mapper.alternativeMap(item));
            }
        }
        fileOperation.saveAllLines(lines);
    }
}
