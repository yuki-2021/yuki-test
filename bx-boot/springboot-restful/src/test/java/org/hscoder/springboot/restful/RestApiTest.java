package org.hscoder.springboot.restful;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hscoder.springboot.restful.modules.rest.RestDataManager;
import org.hscoder.springboot.restful.modules.rest.domain.Pet;
import org.hscoder.springboot.simplebuild.DemoBoot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Rest 接口API测试
 * 
 * @author atp
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoBoot.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestApiTest {

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RestDataManager dataManager;

    private static final String CUSTOMER = "LiLei";
    private Pet polly;
    private Pet badboy;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        initData();
    }

    private void initData() {
        // 清除原有宠物信息
        dataManager.clearPets(CUSTOMER);

        // 添加新的宠物信息
        polly = new Pet();
        polly.setType("Bird");
        polly.setName("Polly");
        polly.setDescription("the rapid speaker");

        dataManager.addPet(CUSTOMER, polly);

        badboy = new Pet();
        badboy.setType("Dog");
        badboy.setName("BadBoy");
        polly.setDescription("the monster");

        dataManager.addPet(CUSTOMER, badboy);
    }

    @Test
    public void testAdd() throws Exception {

        Pet addPet = new Pet();
        addPet.setType("Fish");
        addPet.setName("The killer");
        addPet.setDescription("she eat meat");

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/pets/{customer}", CUSTOMER)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(addPet)))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testList() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/pets/{customer}", CUSTOMER))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(
                        MockMvcResultMatchers.content().json(mapper.writeValueAsString(Arrays.asList(polly, badboy))))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testGet() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/pets/{customer}/{petId}", CUSTOMER, polly.getPetId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(polly)))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testRemove() throws Exception {

        // delete
        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/pets/{customer}/{petId}", CUSTOMER, polly.getPetId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // return not found
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/rest/pets/{customer}/{petId}", CUSTOMER, polly.getPetId()))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andDo(MockMvcResultHandlers.print())
                .andReturn();

        String respContent = result.getResponse().getContentAsString();
        assertTrue(respContent.contains("pet"));

    }

    @Test
    public void testUpdate() throws Exception {

        Pet newPolly = new Pet();
        newPolly.setType("Bird");
        newPolly.setName("New Polly");
        newPolly.setDescription("old enough");

        // update
        mockMvc.perform(MockMvcRequestBuilders.put("/rest/pets/{customer}/{petId}", CUSTOMER, polly.getPetId())
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(newPolly)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // the information changed
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/pets/{customer}/{petId}", CUSTOMER, polly.getPetId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Polly"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testGetNotFound() throws Exception {

        // customer not found
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/pets/{customer}", "guest"))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }

}
