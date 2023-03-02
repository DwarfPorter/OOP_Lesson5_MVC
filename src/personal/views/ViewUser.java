package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());

                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        caseCreate();
                        break;
                    case READ:
                        caseRead();
                        break;
                    case LIST:
                        caseList();
                        break;
                    case DELETE:
                        caseDelete();
                        break;
                    case UPDATE:
                        caseUpdate();
                        break;
                }
            } catch (Exception e) {
                System.out.printf("Error: %s" + System.lineSeparator(), e.getMessage());
            }
        }
    }

    private void caseList() {
        List<User> users = userController.listUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void caseRead() {
        String id = prompt("Идентификатор пользователя: ");
        try {
            User user = userController.readUser(id);
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void caseCreate() throws Exception {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        userController.saveUser(new User(firstName, lastName, phone));
    }

    private void caseDelete() {
        String id = prompt("Введите id пользователя: ");
        userController.deleteUser(id);
    }

    private void caseUpdate() {
        String id = prompt("Введите id пользователя: ");
        try {
            User user = userController.readUser(id);
            String field = prompt("Какое поле изменить? " + System.lineSeparator() +
                    "1: имя" + System.lineSeparator() +
                    "2: фамилия" + System.lineSeparator() +
                    "3: номер телефона" + System.lineSeparator());
            String newData = prompt("Введите новое значение: ");
            switch (field){
                case "1":
                    user.setFirstName(newData);
                    break;
                case "2":
                    user.setLastName(newData);
                    break;
                case "3":
                    user.setPhone(newData);
                    break;
                default:
                    throw new Exception("Некорректный ввод");
            }
            userController.updateUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
