package com.yuki.demo5.anno;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:anno/b.xml")
@Import(value = CarConfig.class)
public class AppConfig {


    @Bean
    public UserService userService(@Qualifier("car6") Car car) {
        return new UserService(car);
    }

    @Bean
    public UserController userController(UserService userService,@Qualifier("car666") Car car) {
        return new UserController(userService,car);
    }
}
