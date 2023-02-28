package personal.views;

public enum HelpEnum {
    MAIN_MENU("""
            READ - посмотреть контакт
            CREATE - создать контакт
            UPDATE - изменит контакт
            DELETE - удалить контакт
            HELP - помощь
            LIST - посмотреть все контакты
            EXIT - выход
            """),
    UPDATE_MENU("""
            FIRSTNAME - изменить имя контакта
            LASTNAME - изменить фамилмю контакта
            PHONE - изменить номер контакта
            BACK - назад в главное меню
            """);


    private final String text;
    HelpEnum(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
