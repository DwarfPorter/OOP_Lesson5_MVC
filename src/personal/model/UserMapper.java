package personal.model;

public interface UserMapper {
    String map(User user);
    String alternativeMap(User user);
    User map(String line);
    User alternativeMap(String line);
}
