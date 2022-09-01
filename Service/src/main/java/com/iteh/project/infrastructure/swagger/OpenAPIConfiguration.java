package com.iteh.project.infrastructure.swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI openAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("Servis studentske sluzbe")
                        .description("Prijava predmeta i prijava ispita")
                        .version("v1.0")
                        .contact(
                                new Contact()
                                        .name("Fakultet Organizacionih nauka")
                        ));
    }
}
