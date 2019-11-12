package org.hscoder.springboot.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;

@TestConfiguration
@Profile("test")
public class TestConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database", "test");
    }

    @Override
    public Mongo mongo() throws Exception {
        return new Fongo(getDatabaseName()).getMongo();
    }
    
}
