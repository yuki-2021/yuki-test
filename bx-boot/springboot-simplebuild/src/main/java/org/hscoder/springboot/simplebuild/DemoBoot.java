package org.hscoder.springboot.simplebuild;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

/**
 * 入口类
 *
 */
@SpringBootApplication
public class DemoBoot {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(DemoBoot.class);

        // 指定PID生成，默认输出到application.pid
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }
}
