package personal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            users.add(mapper.map(line));
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
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
        return id;
    }

    @Override
    public String updateUser(String id) {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId().equals(id)) {
                String firstName = prompt("Имя: ");
                String lastName = prompt("Фамилия: ");
                String phone = prompt("Номер телефона: ");
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPhone(phone);
            }
        }
        List<String> newlines = new ArrayList<>();
        for (User item : users) {
            newlines.add(mapper.map(item));
        }
        if (lines.equals(newlines)){
            System.out.println("По указанному ID нет User в базе данных.");
            return null;
        }
        fileOperation.saveAllLines(newlines);
        return null;
    }

    @Override
    public String deleteUser(String id) {
        List<String> lines = fileOperation.readAllLines();
        List<String> newlines = new ArrayList<>();
        for (String line : lines) {
            String[] newline = line.split(",");
            if (!newline[0].equals(id)) {
                newlines.add(line);
            }
        }
        if (lines.equals(newlines)){
            System.out.println("Под указанным ID отсутствует искомый пользователь.");
            return id;
        }
        fileOperation.saveAllLines(newlines);
        return id;
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
