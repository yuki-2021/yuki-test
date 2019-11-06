package com.yuki.eureka.consumer;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "upload-server",configuration = UploadService.MultipartSupportConfig.class)
public interface UploadService {

    @Configuration
    class MultipartSupportConfig{

        //配置Feign编码器和解码器
        @Bean
        public Encoder feignFormEncoder(){
            return new SpringFormEncoder();
        }
    }

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestPart(value = "file") MultipartFile file);

}
