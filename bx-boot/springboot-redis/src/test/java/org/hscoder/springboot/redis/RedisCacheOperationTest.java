package org.hscoder.springboot.redis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootSampleRedis.class)
public class RedisCacheOperationTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheOperationTest.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisCacheOperation operation;

    private RedisPet pet1 = new RedisPet("Polly", "Bird");

    @Test
    public void testGet() {
        operation.getPet(pet1.getName());

        Object object = redisTemplate.opsForValue().get(RedisCacheOperation.PREFIX + pet1.getName());
        logger.info(String.valueOf(object));

        assertNotNull(object);
    }

    @Test
    public void testUpdate() {
        operation.updatePet(pet1);

        Object object = redisTemplate.opsForValue().get(RedisCacheOperation.PREFIX + pet1.getName());
        logger.info(String.valueOf(object));

        assertNull(object);
    }

    @Test
    public void testDelete() {
        operation.getPet(pet1.getName());

        // delete cache
        operation.deletePet(pet1.getName());

        Object object = redisTemplate.opsForValue().get(RedisCacheOperation.PREFIX + pet1.getName());
        logger.info(String.valueOf(object));

        assertNull(object);
    }

}
