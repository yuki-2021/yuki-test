package org.hscoder.springboot.jpa.repository;

import org.hscoder.springboot.jpa.BootJpa;
import org.hscoder.springboot.jpa.domain.CityView;
import org.hscoder.springboot.simplebuild.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 在Script为空的情况下
 * h2 url = jdbc:h2:mem:test
 * h2 schema = classpath:script/test-data.sql
 * jpa 是 update
 * 会直接创建查询为空
 * 制定了script的情况下，script不能为空
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootJpa.class)
public class CityViewRepositoryTest {

    @Autowired
    private CityViewRepository cityViewRepository;

    @Test
    public void testGetAll(){

        List<CityView> views = cityViewRepository.findAll();
        System.out.println(JsonUtil.toPrettyJson(views));
    }

}
