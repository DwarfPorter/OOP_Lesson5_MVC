package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;
        String id;
        String firstName;
        String lastName;
        String phone;

        while (true) {
            String command = prompt("Введите команду: ");
            try {
                com = Commands.valueOf(command);
            }
            catch (Exception e){
                System.out.println("Command not found");
            }
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    firstName = prompt("Имя: ");
                    lastName = prompt("Фамилия: ");
                    phone = prompt("Номер телефона: ");
                    userController.saveUser(new User(firstName, lastName, phone));
                    break;
                case UPDATE:
                    id = prompt("Идентификатор пользователя: ");
                    firstName = prompt("Имя: ");
                    lastName = prompt("Фамилия: ");
                    phone = prompt("Номер телефона: ");
                    try {
                        userController.updateUser(id, firstName, lastName, phone);
                    } catch (Exception e) {
                        System.out.println("User not found!");
                        throw new RuntimeException(e);
                    }
                    break;
                case READ:
                    id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(id);
                        System.out.println(user);
                    } catch (Exception e) {
                        System.out.println("User not found!");
                        throw new RuntimeException(e);
                    }
                    break;
                case DELETE:
                    id = prompt("Идентификатор пользователя: ");
                    try {
                        userController.deleteUser(id);
                    } catch (Exception e) {
                        System.out.println("User not found!");
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
