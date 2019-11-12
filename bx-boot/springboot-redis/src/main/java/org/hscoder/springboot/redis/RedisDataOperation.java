package org.hscoder.springboot.redis;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.hscoder.springboot.simplebuild.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class RedisDataOperation {

    private static final Logger logger = LoggerFactory.getLogger(RedisDataOperation.class);

    @Autowired
    private PetRepository petRepo;

    @PostConstruct
    public void start() {

        RedisPet pet1 = new RedisPet("Polly", "Bird");
        RedisPet pet2 = new RedisPet("Tom", "Cat");

        //写入宠物信息
        petRepo.add(pet1);
        petRepo.add(pet2);

        //打印宠物信息
        logger.info("polly {}", JsonUtil.toJson(petRepo.find("Polly")));
        logger.info("pets  {}", JsonUtil.toJson(petRepo.findAll()));

        //清空
        petRepo.clear();
    }

    @Repository
    public static class PetRepository {

        private static final String KEY = "Pets";

        @Autowired
        private RedisTemplate<String, Object> redisTemplate;
        private HashOperations<String, String, Object> hashOperations;

        @PostConstruct
        private void init() {
            hashOperations = redisTemplate.opsForHash();
        }

        /**
         * 添加宠物
         * 
         * @param pet
         */
        public void add(RedisPet pet) {
            hashOperations.put(KEY, pet.getName(), pet);
        }

        /**
         * 查找宠物
         * 
         * @param name
         * @return
         */
        public RedisPet find(String name) {
            return (RedisPet) hashOperations.get(KEY, name);
        }

        public Map<String, Object> findAll() {
            return hashOperations.entries(KEY);
        }

        public void clear() {
            hashOperations.getOperations().delete(KEY);
        }
    }

}
