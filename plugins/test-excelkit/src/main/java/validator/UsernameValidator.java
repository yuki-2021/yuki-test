package validator;

import com.wuwenze.poi.validator.Validator;

public class UsernameValidator implements Validator {


    public String valid(Object o) {
        return String.valueOf(o).length() > 6 ? "长度不合法" : null;
    }
}
