package com.yuki.entry;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {

   //数据库主键
   @TableId
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
