package com.yuki.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 设置自动填充
 *
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("start insert fill ....");
        this.setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
//        boolean createTime = metaObject.hasSetter("createTime");
//        if(!createTime){
//            this.setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
//        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("start update fill ....");
        this.setInsertFieldValByName("updateTime", LocalDateTime.now(),metaObject);
    }
}
