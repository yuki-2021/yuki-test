package entity;


import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import validator.CustomizeFieldReadConverter;
import validator.CustomizeFieldWriteConverter;
import validator.UserEmailValidator;
import validator.UsernameValidator;

import java.util.Date;

@Excel("用户信息")
public class User {

    @ExcelField(value = "编号", width = 30)
    private Integer userId;

    @ExcelField(value = "用户名",required = true,validator = UsernameValidator.class,comment = "用户名称最大6")
    private String username;

    @ExcelField(value = "密码", required = true, maxLength = 6)
    private String password;

    @ExcelField(value = "邮箱", validator = UserEmailValidator.class)
    private String email;

    @ExcelField(//
            value = "性别",//
            readConverterExp = "未知=0,男=1,女=2",//
            writeConverterExp = "0=未知,1=男,2=女",//
            options = SexOptions.class//
    )
    private Integer sex;

    @ExcelField(//
            value = "用户组",//
            name = "userGroup.name",//
            options = UserGroupNameOptions.class
    )
    private UserGroup userGroup;

    @ExcelField(value = "创建时间", dateFormat = "yyyy/MM/dd HH:mm:ss")
    private Date createAt;

    @ExcelField(//
            value = "自定义字段",//
            maxLength = 6,//
            comment = "可以乱填，但是长度不能超过80，导入时最终会转换为数字",//
            writeConverter = CustomizeFieldWriteConverter.class,// 写文件时，将数字转字符串
            readConverter = CustomizeFieldReadConverter.class// 读文件时，将字符串转数字
    )
    private Integer customizeField;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getCustomizeField() {
        return customizeField;
    }

    public void setCustomizeField(Integer customizeField) {
        this.customizeField = customizeField;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("userId=").append(userId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", sex=").append(sex);
        sb.append(", userGroup=").append(userGroup);
        sb.append(", createAt=").append(createAt);
        sb.append(", customizeField=").append(customizeField);
        sb.append('}');
        sb.append("=================================");
        return sb.toString();
    }
}
