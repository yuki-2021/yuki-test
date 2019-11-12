package org.hscoder.springboot.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class})
public class BootSampleMongo {

    public static void main(String[] args) {
        SpringApplication.run(BootSampleMongo.class);
    }

}
