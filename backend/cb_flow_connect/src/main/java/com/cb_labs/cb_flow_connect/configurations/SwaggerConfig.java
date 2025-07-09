package com.cb_labs.cb_flow_connect.configurations;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        Contact contact = new Contact();
        contact.setName("GitHub repository");
        contact.setUrl("https://github.com/Crypto-Bros-Labs/bitso_hack");

        Info info = new Info();
        info.title("CB Flow Connect API");
        info.description("On and off ramp high level API");
        info.contact(contact);

        SecurityRequirement requirement = new SecurityRequirement();
        requirement.addList("JWTAuthentication");

        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.name("JWTAuthentication");
        securityScheme.type(SecurityScheme.Type.HTTP);
        securityScheme.scheme("bearer");
        securityScheme.bearerFormat("JWT");

        Components components = new Components();
        components.addSecuritySchemes("JWTAuthentication", securityScheme);

        Server development = new Server();
        development.setUrl("http://gcloud");
        development.setDescription("Development server");

        Server localhost = new Server();
        localhost.setUrl("http://localhost:8080");
        localhost.setDescription("Local server");

        return new OpenAPI()
                .info(info)
                .addSecurityItem(requirement)
                .components(components)
                .servers(List.of(development, localhost));
    }

}
