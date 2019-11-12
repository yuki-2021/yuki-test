package org.hscoder.springboot.restful;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author sdc57
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public static final String VERSION = "1.0.0";

    @Value("${swagger.enable}")
    private boolean enabled;


    //生成API文档的基本信息
    ApiInfo apiInfo() {
        return new ApiInfoBuilder().
                title("Pet Api Definition")
                .description("The Petstore CRUD Example")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version(VERSION)
                .contact(new Contact("", "", "zalesfoo@163.com"))
                .build();
    }


    @Bean
    public Docket customImplementation() {
        //Docket地址
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .build()
                .enable(enabled)
                .apiInfo(apiInfo());
    }
}