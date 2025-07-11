package com.EduHubAcademy.asignaturaService.config;


import com.EduHubAcademy.docenteService.security.JwtAutenticacionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
    @Configuration
    public class SecurityConfig {
    @Autowired
    private JwtAutenticacionFilter jwtAuthenticationFilter;


    //Solo  usuarios autenticados (con JWT válido) puedan inscribirse o modificar datos.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> {

                        })
                );

        return http.build();
    }
}






