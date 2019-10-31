package validator;

import com.wuwenze.poi.validator.Validator;

public class UserEmailValidator implements Validator {
    public String valid(Object o) {
        return String.valueOf(o).contains("@") ? null : "邮箱不合法";
    }
}
