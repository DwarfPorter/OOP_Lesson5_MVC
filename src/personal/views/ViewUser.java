package personal.views;

import personal.controllers.UserController;
import personal.model.Delimiter;
import personal.model.User;

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
                    case CREATE -> create();
                    case READ -> read();
                    case LIST -> list();
                    case DELETE -> delete();
                    case UPDATE -> update();
                    case SAVEMODE -> saveMode();
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Неизвестная команда.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void saveMode() throws Exception {
        String delimiter = prompt("Введите название разделителя: ").toUpperCase();
        try {
            delimiter = Delimiter.valueOf(delimiter).getDelimiter();
            userController.saveMode(delimiter);
            System.out.println("Разделитель изменен на " + delimiter);
        } catch (Exception e){
            throw new Exception("Разделитель не найден ");
        }
    }

    private void update() {
        String id = prompt("Введите id пользователя ");
        if (userController.hasUpdateUser(id)) {
            if(userController.updateUser(id, newUser()))
                System.out.println("Пользователь изменен ");
        } else {
            System.out.println("Пользователь не найден ");
        }
    }

    private void delete() {
        String id = prompt("Введите id пользователя ");
        if (userController.deleteUser(id)) {
            System.out.println("Пользователь удален ");
        } else {
            System.out.println("Пользователь не найден ");
        }
    }

    private void list() {
        for (User user : userController.readAllUsers()) {
            System.out.println(user);
        }
    }

    private void read() {
        String id = prompt("Идентификатор пользователя: ");
        try {
            User user = userController.readUser(id);
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void create() throws Exception {
        userController.saveUser(newUser());
        System.out.println("Пользователь добавлен ");
    }

    private User newUser(){
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new  User(firstName, lastName, phone);
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
