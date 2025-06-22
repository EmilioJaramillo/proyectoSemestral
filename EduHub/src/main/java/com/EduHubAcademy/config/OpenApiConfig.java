package com.EduHubAcademy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("EduHub")
                .version("1.0")
                .description("EduHub – API para Administradores, Docentes, Estudiantes y Soporte"));
    }
    
}