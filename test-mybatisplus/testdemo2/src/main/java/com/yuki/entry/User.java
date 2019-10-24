package com.yuki.entry;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.yuki.enums.SexEnum;
import lombok.*;
import org.apache.ibatis.type.EnumTypeHandler;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {

   //数据库主键
   @TableId(type = IdType.ID_WORKER)
   private Long id;
   private String name;
   private Integer age;
   private String email;
   private Long managerId;
   @TableField(fill = FieldFill.INSERT)
   private LocalDateTime createTime;
   @TableField(fill = FieldFill.INSERT_UPDATE)
   private LocalDateTime updateTime;
   @Version
   private Integer version;
   @TableLogic
   private Integer deleted;
   @TableField(typeHandler = EnumTypeHandler.class)
   private SexEnum sex;


   //不插入使用transient
  // private transient String remark;
//   private static transient String remark;
//
//   public static String getRemark() {
//      return remark;
//   }
//
//   public static void setRemark(String remark) {
//      User.remark = remark;
//   }
}
