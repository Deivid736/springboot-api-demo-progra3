package com.ejemplo.demo.metadata;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerMetadata {
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Workshop Spring Boot 3.x")
                        .version("2.6.0")
                        .description("Workshop de la UMG")
                        .contact(new Contact()
                                .name("Deivid Alberto Guerra Carpio")
                                .email("dguerrac7@miumg.edu.gt")));



}
}
