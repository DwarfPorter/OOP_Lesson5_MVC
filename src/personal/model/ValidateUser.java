package personal.model;

import java.util.regex.Pattern;

public class ValidateUser {

    private Pattern patternName = Pattern.compile("^\\S*$");
    private Pattern patternPhone = Pattern.compile("^\\d*$");
    public void check(User user) throws Exception {
        if (!patternName.matcher(user.getFirstName()).find()) {
            throw new Exception("First name isn't correct");
        }
        if (!patternName.matcher(user.getLastName()).find()) {
            throw new Exception("Last name isn't correct");
        }
        if (!patternPhone.matcher(user.getPhone()).find()) {
            throw new Exception("Phone isn't correct");
        }
    }


}


