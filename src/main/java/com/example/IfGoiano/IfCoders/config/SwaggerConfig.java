package com.example.IfGoiano.IfCoders.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


@Configuration
public class SwaggerConfig {

    @Value("${swagger.url}")
    private String swaggerUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("IF-Inclusivo API")
                        .description("Esta API faz parte de um projeto de extensão desenvolvido na disciplina Projeto " +
                                "Integrador. O objetivo é integrar alunos e professores em um aplicativo de fórum e glossário de Libras.")
                        .version("1.0.0")
                )
                .servers(Collections.singletonList(new Server().url(swaggerUrl)))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT") // apenas informativo
                        )
                );
    }
}
