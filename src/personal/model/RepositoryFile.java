package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private Mapper mapper;
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation, Mapper mapper) {
        this.fileOperation = fileOperation;
        this.mapper = mapper;
    }

    public void setDelimiter(String delimiter){
        // Получаем пользователей с прошлым разделителем
        List<User> users = getAllUsers();

        // Устанавливаем новый разделитель
        this.mapper.setDelimiter(delimiter);

        // Сохраняем пользователей с новым разделителем
        fileOperation.saveAllLines(getLinesToSave(users));
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
    public boolean deleteUser(String id) {
        List<User> users = getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                fileOperation.saveAllLines(getLinesToSave(users));
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasUpdateUser(String id){
        for (User user : getAllUsers()){
            if (user.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(String id, User user) {
        List<User> users = getAllUsers();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.get(i).setFirstName(user.getFirstName());
                users.get(i).setLastName(user.getLastName());
                users.get(i).setPhone(user.getPhone());
                fileOperation.saveAllLines(getLinesToSave(users));
                return true;
            }
        }

        return false;
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
        fileOperation.saveAllLines(getLinesToSave(users));
        return id;
    }

    private List<String> getLinesToSave(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item : users) {
            lines.add(mapper.map(item));
        }
        return lines;
    }
}
