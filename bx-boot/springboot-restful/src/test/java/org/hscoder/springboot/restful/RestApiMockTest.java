package org.hscoder.springboot.restful;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hscoder.springboot.restful.modules.rest.RestDataManager;
import org.hscoder.springboot.restful.modules.rest.domain.Customer;
import org.hscoder.springboot.restful.modules.rest.domain.Pet;
import org.hscoder.springboot.simplebuild.DemoBoot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

/**
 * Rest 接口+Mockito测试
 * 
 * @author atp
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoBoot.class, webEnvironment = WebEnvironment.MOCK)
public class RestApiMockTest {

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    // 实现mock
    @Mock
    private RestDataManager dataManager;

    // 依赖mock
    @InjectMocks
    private RestApiController controller;

    private static final String CUSTOMER = "LiLei";

    private Pet polly;
    private Customer lilei;

    @Before
    public void setupMockMvc() throws Exception {

        // 启用mock
        MockitoAnnotations.initMocks(this);

        polly = new Pet();
        polly.setType("Bird");
        polly.setName("Polly");
        polly.setDescription("the rapid speaker");

        lilei = new Customer();
        lilei.setName(CUSTOMER);

        // 设置mock接口
        Mockito.when(dataManager.getPets(Mockito.isA(String.class))).thenReturn(Arrays.asList(polly));
        Mockito.when(dataManager.getCustomer(Mockito.isA(String.class))).thenReturn(lilei);

        // 使用standaloneSetup，指定controller
        // 由于不通过webappliationContext初始化，许多配置不会自动完成，此外bean的初始化方法也不会执行
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testMockito() throws Exception {

        // 返回数据格式，通过
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/pets/{customer}", CUSTOMER))
                // .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(Arrays.asList(polly))))
                .andDo(MockMvcResultHandlers.print());
    }

}
