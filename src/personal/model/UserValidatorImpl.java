package personal.model;

import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {
    private Pattern reName = Pattern.compile("^\\S$*");
    private Pattern rePhone = Pattern.compile("^\\d$*");
    @Override
    public boolean nameValidation(User user) throws Exception {
        if(!reName.matcher(user.getFirstName()).find()){
            throw new Exception("Bad first name");
        }
        if(!reName.matcher(user.getLastName()).find()){
            throw new Exception("Bad last name");
        }
        if(!rePhone.matcher(user.getPhone()).find()){
            throw new Exception("Bad phone number");
        }
        return true;
    }


}
