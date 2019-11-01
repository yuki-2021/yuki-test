package com.yuki.param.validator.validator;

import com.yuki.param.validator.annotation.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;

public class DateTimeValidator implements ConstraintValidator<DateTime, String> {

   private DateTime dateTime;

   //主要用于初始化，它可以获得当前注解的所有属性
   @Override
   public void initialize(DateTime dateTime) {
      this.dateTime = dateTime;
   }

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
      // 如果 value 为空则不进行格式验证，为空验证可以使用 @NotBlank @NotNull @NotEmpty 等注解来进行控制，职责分离
      if(value == null){
         return true;
      }
      String format = dateTime.format();
      if(format.length() != value.length()){
         return false;
      }
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
      try {
         simpleDateFormat.format(value);
      } catch (Exception e) {
         return false;
      }
      return true;
   }
}
