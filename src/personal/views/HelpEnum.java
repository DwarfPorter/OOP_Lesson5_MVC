package personal.views;

public enum HelpEnum {
    MAIN_MENU("READ - посмотреть контакт\n" +
            "CREATE - создать контакт\n" +
            "UPDATE - изменит контакт\n" +
            "DELETE - удалить контакт\n" +
            "HELP - помощь\n" +
            "LIST - посмотреть все контакты\n" +
            "EXIT - выход\n"),
    UPDATE_MENU("FIRSTNAME - изменить имя контакта\n" +
            "LASTNAME - изменить фамилмю контакта\n" +
            "PHONE - изменить номер контакта\n" +
            "BACK - назад в главное меню\n");


    private final String text;
    HelpEnum(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
