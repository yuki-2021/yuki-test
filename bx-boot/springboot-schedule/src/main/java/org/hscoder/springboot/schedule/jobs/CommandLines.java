package org.hscoder.springboot.schedule.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class CommandLines {

    private static final Logger logger = LoggerFactory.getLogger(CommandLines.class);

    @Profile("async")
    @Component
    @Order(1)
    public static class CommandLineAppStartupRunner implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {
            logger.info(
                    "[CommandLineRunner]Application started with command-line arguments: {} .To kill this application, press Ctrl + C.",
                    Arrays.toString(args));
        }
    }
    
    @Profile("async")
    @Component
    @Order(2)
    public static class AppStartupRunner implements ApplicationRunner {
        
        @Override
        public void run(ApplicationArguments args) throws Exception {
            logger.info("[ApplicationRunner]Your application started with option names : {}", args.getOptionNames());
        }
    }
}
