package org.hscoder.springboot.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 方法级缓存样例
 * 
 * @author atp
 *
 */
@Service
public class RedisCacheOperation {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheOperation.class);

    public static final String PREFIX = "pets:";
    public static final String WRAP_PREFIX = "'pets:'";

    /**
     * 当结果不为空时缓存
     * 
     * @param name
     * @return
     */
    @Cacheable(value = "petCache", key = WRAP_PREFIX + "+#name", unless = "#result==null")
    public RedisPet getPet(String name) {
        logger.info("get pet {}", name);
        return new RedisPet(name, "Bird");
    }

    /**
     * 当结果不为空时淘汰缓存
     * 
     * @param pet
     * @return
     */
    @CacheEvict(value = "petCache", key = WRAP_PREFIX + "+#pet.name", condition = "#result!=null")
    public RedisPet updatePet(RedisPet pet) {
        logger.info("update pet {}", pet.getName());
        return new RedisPet(pet.getName(), "Bird1");
    }

    /**
     * 当结果为true时淘汰缓存
     * 
     * @param name
     * @return
     */
    @CacheEvict(value = "petCache", key = WRAP_PREFIX + "+#name", condition = "#result==true")
    public boolean deletePet(String name) {
        logger.info("delete pet {}", name);
        return true;
    }
}
