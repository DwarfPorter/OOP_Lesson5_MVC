package personal.model;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.indexOf;

// Класс для работы с файлом
public class RepositoryFile implements Repository {
    private Mapper mapper;
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation, Mapper mapper) { // "Инъекция" - внедряем интерфейс FileOperation через конструктор
        this.fileOperation = fileOperation;
        this.mapper = mapper;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.mapSemicolon(line));
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
        saveRepository(users);
        return id;
    }

    private void saveRepository(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item : users) {
            lines.add(mapper.mapWithSemicolon(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public void deleteUser(String userId) {
        List<User> users = getAllUsers();
        User foundUser = null;

        for (User user : users) {
            if (user.getId().equals(userId)) {
                foundUser = user;
            }
        }

        if (foundUser != null) {
            users.remove(foundUser);
            System.out.println("User deleted");
        } else {
            System.out.println("Некорректный id");
        }

        saveRepository(users);
    }

    @Override
    public void updateUser(User user) {
        List<User> users = getAllUsers();

        int index = 0;
        for (User item : users) {
            if (item.getId().equals(user.getId())) {
                users.set(index, user);
                break;
            }
            index++;
        }
        saveRepository(users);
    }
}


