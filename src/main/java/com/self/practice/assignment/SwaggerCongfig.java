package com.self.practice.assignment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//issue (had to downgrade to spring 2.5.6 from 2.6.0 )- > https://stackoverflow.com/questions/70036953/springboot-2-6-0-spring-fox-3-failed-to-start-bean-documentationpluginsboot
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerCongfig implements WebMvcConfigurer{
    //bean docket
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2);
    }
    //swagger2
    //all the paths
    //all the apis
}
