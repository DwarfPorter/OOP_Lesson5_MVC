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
                System.out.println("Основное меню\n");
                String command = prompt("Введите команду (введите help для просмтора доступных команд): ");
                com = Commands.valueOf(command.toUpperCase());
                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE -> createUser();
                    case READ -> showUser();
                    case LIST -> showAllUsers();
                    case DELETE -> deleteUser();
                    case UPDATE -> {
                        checkBase();
                        updateUserInfo();
                    }
                    case HELP -> System.out.println(HelpEnum.MAIN_MENU);
                }
            } catch (Exception e) {
                System.out.printf("Something wrong - %s\n", e.getMessage());
            }
        }
    }

    private int chooseTypeOfFormat() {
        try {
            return Integer.parseInt(prompt("Введите 1 для записи через запятую, 2 для записи через точку с запятой: "));
        } catch (Exception e) {
            return -1;
        }
    }

    private void createUser() {
        int typeOfFormat = chooseTypeOfFormat();
        if (typeOfFormat == -1) {
            System.out.println("Something wrong\n");
            return;
        }
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        userController.saveUser(new User(firstName, lastName, phone, typeOfFormat));
        System.out.println("Contact has been successfully added\n");
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
            System.out.println("Deleted successfully\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateUserInfo() {
        Commands com;
        try {
            System.out.println("Меню изменения контакта\n");
            String command = prompt("Введите команду (введите help для просмтора доступных команд): ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case BACK -> run();
                case FIRSTNAME -> updateFirstName();
                case LASTNAME -> updateLastName();
                case PHONE -> updatePhoneNumber();
                case HELP -> System.out.println(HelpEnum.UPDATE_MENU);
            }
        } catch (Exception e) {
            System.out.printf("Something wrong - %s\n", e.getMessage());
        }


    }

    private void updateFirstName() {
        String id = prompt("Идентификатор пользователя: ");
        String newInfo = prompt("Введите новое имя: ");
        try {
            userController.updateUser(id, newInfo, 1);
            System.out.println("Firstname changed\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateLastName() {
        String id = prompt("Идентификатор пользователя: ");
        String newInfo = prompt("Введите новую фамилию: ");
        try {
            userController.updateUser(id, newInfo, 1);
            System.out.println("Lastname changed\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePhoneNumber() {
        String id = prompt("Идентификатор пользователя: ");
        String newInfo = prompt("Введите новый телефон: ");
        try {
            userController.updateUser(id, newInfo, 1);
            System.out.println("Phone number changed\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkBase() throws Exception {
        if (userController.readAllUsers().isEmpty()) {
            throw new Exception("Base is empty\n");
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
