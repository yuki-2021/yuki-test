package org.hscoder.springboot.web;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

/**
 * 参数校验样例
 * 
 * @author atp
 *
 */
@Controller
@RequestMapping("/validate")
@Validated
public class ValidateController {

    private static final Logger logger = LoggerFactory.getLogger(ValidateController.class);

    /**
     * 路径参数
     * 
     * @param group
     * @param userid
     * @return
     */
    @GetMapping("/path/{group:[a-zA-Z0-9_]+}/{userid}")
    @ResponseBody
    public String path(@PathVariable("group") String group, @PathVariable("userid") Integer userid) {

        return group + ":" + userid;
    }

    /**
     * 请求参数
     * 
     * @param group
     * @param userid
     * @return
     */
    @GetMapping("/param")
    @ResponseBody
    public String param(@RequestParam("group") @Email String email, @RequestParam("userid") Integer userid) {

        return email + ":" + userid;
    }

    /**
     * 表单绑定
     * 
     * @param group
     * @param userid
     * @return
     */
    @PostMapping("/form")
    @ResponseBody
    public FormRequest form(@Validated FormRequest form) {

        return form;
    }

    /**
     * Json绑定
     * 
     * @param request
     * @return
     */
    @PostMapping("/json")
    @ResponseBody
    public JsonRequest json(@Validated @RequestBody JsonRequest request) {

        return request;
    }

    public static class FormRequest {

        @NotEmpty
        @Email
        private String email;

        @Pattern(regexp = "[a-zA-Z0-9_]{6,30}")
        private String name;

        @Min(5)
        @Max(199)
        private int age;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }

    public static class JsonRequest {

        @NotEmpty
        @Email
        private String email;

        @Pattern(regexp = "[a-zA-Z0-9_]{6,30}")
        private String name;

        @Min(5)
        @Max(199)
        private int age;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }

    @ControllerAdvice(assignableTypes=ValidateController.class)
    public static class CustomExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value = { ConstraintViolationException.class })
        public ResponseEntity<String> handle(ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            StringBuilder strBuilder = new StringBuilder();
            for (ConstraintViolation<?> violation : violations) {
                strBuilder.append(violation.getInvalidValue() + " " + violation.getMessage() + "\n");
            }
            String result = strBuilder.toString();
            return new ResponseEntity<String>("ConstraintViolation:" + result, HttpStatus.BAD_REQUEST);
        }

        @Override
        protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                WebRequest request) {
            return new ResponseEntity<Object>("BindException:" + buildMessages(ex.getBindingResult()),
                    HttpStatus.BAD_REQUEST);
        }

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                HttpHeaders headers, HttpStatus status, WebRequest request) {
            return new ResponseEntity<Object>("MethodArgumentNotValid:" + buildMessages(ex.getBindingResult()),
                    HttpStatus.BAD_REQUEST);
        }

        @Override
        public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                HttpHeaders headers, HttpStatus status, WebRequest request) {
            return new ResponseEntity<Object>("ParamMissing:" + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        @Override
        protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                HttpStatus status, WebRequest request) {
            return new ResponseEntity<Object>("TypeMissMatch:" + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        private String buildMessages(BindingResult result) {
            StringBuilder resultBuilder = new StringBuilder();

            List<ObjectError> errors = result.getAllErrors();
            if (errors != null && errors.size() > 0) {
                for (ObjectError error : errors) {
                    if (error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        String fieldName = fieldError.getField();
                        String fieldErrMsg = fieldError.getDefaultMessage();
                        resultBuilder.append(fieldName).append(" ").append(fieldErrMsg).append(";");
                    }
                }
            }
            return resultBuilder.toString();
        }
    }

}
