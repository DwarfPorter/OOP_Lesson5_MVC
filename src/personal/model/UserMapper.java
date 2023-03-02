package personal.model;

public class UserMapper implements Mapper {
    private String delimiter = ",";

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public String map(User user) {
        return String.format(
                "%s%s%s%s%s%s%s",
                user.getId(), delimiter,
                user.getFirstName(), delimiter,
                user.getLastName(), delimiter,
                user.getPhone());
    }

    @Override
    public User map(String line) {
        String[] lines = line.split(delimiter);
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }
}
