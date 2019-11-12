package org.hscoder.springboot.restful;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * 实现远程测试
 * 
 * @author atp
 *
 */
@RunWith(SpringRunner.class)
@JsonTest
public class RemoteApiTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    /**
     * 注入JacksonTester
     */
    @Autowired
    private JacksonTester<List<?>> json;

    private String url = "http://localhost:8090/";

    @Test
    public void testGet() throws IOException {

        // set required format
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<String> response = restTemplate.exchange(url + "/rest/pets/{customer}", HttpMethod.GET, entity,
                String.class, "LiLei");

        System.out.println("result: " + response.getBody());

        json.parse(response.getBody()).assertThat().asList().isEmpty();

    }
}
