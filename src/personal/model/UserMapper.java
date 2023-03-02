package personal.model;

public class UserMapper implements Mapper {
    @Override
    public String mapWithComma(User user) {
        return String.format("%s,%s,%s,%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    @Override
    public String mapWithSemicolon(User user) {
        return String.format("%s;%s;%s;%s\n", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    @Override
    public User mapComma(String line) {
        String[] lines = line.split(",");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }

    @Override
    public User mapSemicolon(String line) {
        String[] lines = line.split(";");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }

}
