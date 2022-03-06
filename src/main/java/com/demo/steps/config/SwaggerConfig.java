package com.demo.steps.config;

import java.util.*;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * /swagger-ui.html
 */
//http://localhost:8080/todo/api/swagger-ui.html
@Configuration //-> spring se va a encargar de construirlo por atras, este bean no se expone
@EnableSwagger2
public class SwaggerConfig {
    
    public Docket  api(){
        return new Docket( DocumentationType.SWAGGER_2 )
        .select()
        .apis( RequestHandlerSelectors.basePackage("com.demo.steps.controller")) //se cargan nuestros controladores
        .build();
    }

   /* private ApiInfo myApiInfo(){
        return new ApiInfo( 
            "title: The title of my API", 
            "description: some useful description for the API", 
            "version: the versio is 1.0", 
            "termsOfServiceUrl: YOu can see the terms in the following URL..", 
            new Contact("Polo", "www.sample.com", "user@email.com"), 
            "license: Licence MIT", 
            "licenseUrl: Check in the url", 
            Collection.emptyList() );
    }*/
}
