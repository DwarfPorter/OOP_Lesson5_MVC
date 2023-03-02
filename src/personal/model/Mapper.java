package personal.model;

public interface Mapper {
    public String mapWithComma(User user);
    public String mapWithSemicolon(User user);
    public User mapComma(String line);
    public User mapSemicolon(String line);

}
