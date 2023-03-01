package personal;

import personal.controllers.UserController;
import personal.model.*;
import personal.views.ViewUser;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl("users.txt");
        UserMapper mapper = new DefaultUserMapper();
        Repository repository = new RepositoryFile(fileOperation, mapper);
        UserController controller = new UserController(repository);
        ViewUser view = new ViewUser(controller);
        view.run();
    }
}
