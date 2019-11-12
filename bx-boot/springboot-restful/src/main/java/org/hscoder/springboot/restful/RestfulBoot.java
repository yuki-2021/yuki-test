package org.hscoder.springboot.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

@SpringBootApplication
public class RestfulBoot {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(RestfulBoot.class);

        // 指定PID生成，默认输出到application.pid
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }
}
