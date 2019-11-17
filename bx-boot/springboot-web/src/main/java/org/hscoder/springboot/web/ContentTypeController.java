package org.hscoder.springboot.web;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import groovy.util.logging.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 不同内容处理的demo
 * 
 * @author atp
 *
 */
@Controller
@RequestMapping(value = "/content")
@Slf4j
public class ContentTypeController {

    private static final Logger logger = LoggerFactory.getLogger(ContentTypeController.class);

    /**
     * 读取json，返回json
     * 
     * @param jsonData
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/json", consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE },
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> jsonIO(@RequestBody Map<String, Object> jsonData) {

        Map<String, Object> resultData = new HashMap<>(jsonData);
        resultData.put("resultCode", UUID.randomUUID().toString());
        return resultData;
    }

    @ResponseBody
    @PostMapping(value = "/jsonStr")
    public Map<String, Object> jsonStr(@RequestBody String jsonData) {
        logger.info(jsonData);
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("info",jsonData);
        resultData.put("resultCode", UUID.randomUUID().toString());
        return resultData;
    }

    /**
     * 读取xml，返回xml
     * 
     * @param data
     * @return
     */
    @PostMapping(value = "/xml", consumes = {
            MediaType.APPLICATION_XML_VALUE },
            produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ParamData xmlIO(@RequestBody ParamData data) {
        data.setAge(data.getAge() + 1);
        return data;
    }

    /**
     * 读取表单，返回字符串
     * 
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/form", consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE },
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String form(@RequestParam("name") String name, @RequestParam("age") int age) {
        return String.format("Welcome %s, you are %d years old", name, age);
    }

    /**
     * 读取表单绑定对象，返回字符串
     * 
     * @param data
     * @return
     */
    @PostMapping(value = "/form1", consumes = {
            MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String form1(ParamData data) {
        return String.format("Welcome %s, you are %d years old. Bye", data.getName(), data.getAge());
    }

    /**
     * 文件上传
     * 
     * @param name
     * @param file
     * @return
     */
    @PostMapping(value = "file", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String file(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {

        logger.info("file receive {}", name);

        if (file.isEmpty()) {
            return "No File";
        }
        String fileName = file.getOriginalFilename();

        File root = new File("D:/temp");
        if (!root.isDirectory()) {
            root.mkdirs();
        }
        try {
            file.transferTo(new File(root, name));
            return String.format("Upload to %s", fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Upload Failed";
    }

    /**
     * 文件下载
     * 
     * @param name
     * @return
     * @throws IOException
     */
    @GetMapping(path = "/download")
    public ResponseEntity<Resource> download(@RequestParam("name") String name) throws IOException {

        File file = new File("D:/temp", name);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok().header("Content-Disposition", "attachment;fileName=" + name)
                .contentLength(file.length()).body(resource);//.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }

    /**
     * 读取IO流
     * 
     * @param dataStream
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/data", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String rawIO(InputStream dataStream) throws Exception {
        return IOUtils.toString(dataStream, "UTF-8");
    }

    public static class ParamData {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }

    /**
     * 定制
     * 
     * @author atp
     *
     */
    @Configuration
    public static class ContentConfig {

        /**
         * 定制jackson
         * 
         * @return
         */
        @Bean
        public Jackson2ObjectMapperBuilderCustomizer customJackson() {
            return new Jackson2ObjectMapperBuilderCustomizer() {

                @Override
                public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                    //取消null
                    jacksonObjectMapperBuilder.serializationInclusion(Include.NON_NULL);
                    jacksonObjectMapperBuilder.failOnUnknownProperties(false);
                    jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
                }

            };
        }
    }
}
