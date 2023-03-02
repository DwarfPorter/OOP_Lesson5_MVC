package personal.model;

public enum Delimiter {
    COMMA(","),
    SEMICOLON(";"),
    SPACE(" ");
    private final String delimiter;

    Delimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return this.delimiter;
    }
}
