package com.yuki.entry;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

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
   @TableField(value = "name",condition = SqlCondition.NOT_EQUAL)
   private String name;
   private Integer age;
   private String email;
   private Long managerId;
   private LocalDateTime createTime;
   @TableField(exist = false)
   private  String remark;
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
