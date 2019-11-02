package com.yuki.entry;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.yuki.handler.MyHandler;
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
//   @TableField(typeHandler = MyHandler.class)
   private Integer age;
   private String email;
   private Long managerId;
   private LocalDateTime createTime;
   @TableField(exist = false)
   private  String remark;

}
