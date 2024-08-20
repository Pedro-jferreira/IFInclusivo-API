package com.example.IfGoiano.IfCoders.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("IF-Inclusivo api")
                        .description("Esta API faz parte de um projeto de extensão desenvolvido na disciplina Projeto " +
                                "Integrador. O objetivo da disciplina é reunir três professores e grupos de alunos" +
                                " para integrar o conhecimento de várias matérias e, ao final, entregar um projeto" +
                                " de extensão. Neste caso, o projeto é um aplicativo destinado a ajudar pessoas" +
                                " com necessidades específicas. O aplicativo é um fórum onde essas pessoas podem" +
                                " enviar suas dúvidas e receber ajuda de professores e alunos. Além disso, o aplicativo" +
                                " conta com um glossário de Libras, que inclui palavras específicas para programação," +
                                " gerenciado por tutores que adicionam palavras e vídeos de tradução para Libras.")
                        .version("1.0.0")
                )
                .servers(Collections.singletonList(new Server().url("http://localhost:8080")));
    }
}