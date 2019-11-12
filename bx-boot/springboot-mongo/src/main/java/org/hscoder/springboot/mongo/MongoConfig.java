package org.hscoder.springboot.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.util.StringUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@Profile("prod")
public class MongoConfig {

    @Bean
    public MongoDbFactory mongoFactory(MongoProperties mongo) throws Exception {

        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        // 连接池配置
        builder.maxWaitTime(1000 * 60 * 1).socketTimeout(30 * 1000).connectTimeout(10 * 1000).connectionsPerHost(50)
                .minConnectionsPerHost(1).socketKeepAlive(true);

        // 设置鉴权信息
        MongoCredential credential = null;
        if (!StringUtils.isEmpty(mongo.getUsername())) {
            credential = MongoCredential.createCredential(mongo.getUsername(), mongo.getDatabase(),
                    mongo.getPassword());
        }

        MongoClientOptions mongoOptions = builder.build();

        List<ServerAddress> addrs = Arrays.asList(new ServerAddress(mongo.getHost(), mongo.getPort()));
        MongoClient mongoClient = null;
        if (credential != null) {
            mongoClient = new MongoClient(addrs, Arrays.asList(credential), mongoOptions);
        } else {
            mongoClient = new MongoClient(addrs, mongoOptions);
        }
        return new SimpleMongoDbFactory(mongoClient, mongo.getDatabase());
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, context);

        // 去掉_class属性
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        // 自定义转换
        converter.setCustomConversions(customConversions());
        converter.afterPropertiesSet();

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);

        return mongoTemplate;
    } 

    private CustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new BasicDBObjectWriteConverter());
        converters.add(new BasicDBObjectReadConverter());
        return new CustomConversions(converters);
    }

    /**
     * 写入序列化
     * 
     * @author atp
     *
     */
    @WritingConverter
    public static class BasicDBObjectWriteConverter implements Converter<BasicDBObject, String> {

        public String convert(BasicDBObject source) {
            if (source == null) {
                return null;
            }
            return source.toJson();
        }
    }

    /**
     * 读取反序列化
     * 
     * @author atp
     *
     */
    @ReadingConverter
    public static class BasicDBObjectReadConverter implements Converter<String, BasicDBObject> {

        public BasicDBObject convert(String source) {
            if (source == null || source.length() <= 0) {
                return null;
            }
            return BasicDBObject.parse(source);
        }
    }

}
