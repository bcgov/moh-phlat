package com.moh.phlat.backend.security;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        Server server=new Server();
        server.setUrl("/");


        return new OpenAPI().servers(List.of(server))
                .addSecurityItem(new SecurityRequirement()
                                         .addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName, createAPIKeyScheme()));


    }


    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                   .bearerFormat("JWT")
                                   .scheme("bearer");
    }


}