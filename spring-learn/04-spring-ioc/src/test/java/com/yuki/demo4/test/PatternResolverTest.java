package com.yuki.demo4.test;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

public class PatternResolverTest {

    @Test
    public void getResources () throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:conf/*.txt");
        for (Resource resource : resources) {
            System.out.println(resource.getDescription());
        }
    }
}
