package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;
        while (true) {
            try {
                System.out.println("Основное меню");
                String command = prompt("Введите команду (введите help для просмтора доступных команд): ");
                com = Commands.valueOf(command.toUpperCase());
                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        createUser();
                        break;
                    case READ:
                        showUser();
                        break;
                    case LIST:
                        showAllUsers();
                        break;
                    case DELETE:
                        deleteUser();
                        break;
                    case UPDATE:
                        checkBase();
                        updateUserInfo();
                        break;
                    case HELP:
                        System.out.println(HelpEnum.MAIN_MENU);
                        break;
                }
            } catch (Exception e) {
                System.out.printf("Something wrong - %s\n", e.getMessage());
            }
        }
    }

    private void createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        userController.saveUser(new User(firstName, lastName, phone));
        System.out.println("Contact has been successfully added");
    }

    private void showUser() {
        try {
            checkBase();
            String id = prompt("Идентификатор пользователя: ");
            User user = userController.readUser(id);
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showAllUsers() {
        try {
            checkBase();
            List<User> users = userController.readAllUsers();
            for (User item : users) {
                System.out.println(item);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteUser() {
        try {
            checkBase();
            String id = prompt("Идентификатор пользователя: ");
            userController.deleteUser(id);
            System.out.println("Deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateUserInfo() {
        Commands com;
        while (true) {
            try {
                System.out.println("Меню изменения контакта");
                String command = prompt("Введите команду (введите help для просмтора доступных команд): ");
                com = Commands.valueOf(command.toUpperCase());
                if (com == Commands.EXIT) return;
                switch (com) {
                    case BACK:
                        run();
                        break;
                    case FIRSTNAME:
                        updateFirstName();
                        break;
                    case LASTNAME:
                        updateLastName();
                        break;
                    case PHONE:
                        updatePhoneNumber();
                        break;
                    case HELP:
                        System.out.println(HelpEnum.UPDATE_MENU);
                        break;
                }
            } catch (Exception e) {
                System.out.printf("Something wrong - %s\n", e.getMessage());
            }
        }

    }

    private void updateFirstName() {
        String id = prompt("Идентификатор пользователя: ");
        String newInfo = prompt("Введите новое имя: ");
        try {
            userController.updateUser(id, newInfo, 1);
            System.out.println("Firstname changed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateLastName() {
        String id = prompt("Идентификатор пользователя: ");
        String newInfo = prompt("Введите новую фамилию: ");
        try {
            userController.updateUser(id, newInfo, 1);
            System.out.println("Lastname changed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePhoneNumber() {
        String id = prompt("Идентификатор пользователя: ");
        String newInfo = prompt("Введите новый телефон: ");
        try {
            userController.updateUser(id, newInfo, 1);
            System.out.println("Phone number changed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkBase() throws Exception {
        if (userController.readAllUsers().isEmpty()) {
            throw new Exception("Base is empty");
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
