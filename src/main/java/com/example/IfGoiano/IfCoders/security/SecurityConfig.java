package com.example.IfGoiano.IfCoders.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationEntryPointImpl unauthorizedHandler;
    private final AuthenticationFilter authenticationFilter;

    public SecurityConfig(
            AuthenticationEntryPointImpl unauthorizedHandler,
            AuthenticationFilter authenticationFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT stateless
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .requestMatchers(HttpMethod.POST, "/topicos/**").hasRole("PROFESSOR")
                .requestMatchers(HttpMethod.PUT, "/topicos/**").hasRole("PROFESSOR")
                .requestMatchers(HttpMethod.DELETE, "/topicos/**").hasRole("PROFESSOR")
                .requestMatchers(HttpMethod.GET, "/topicos/**").permitAll()

                .requestMatchers(HttpMethod.POST, "/sinais/sugere").permitAll()
                .requestMatchers(HttpMethod.POST, "/sinais/**").hasAnyRole("TUTOR", "INTERPRETE")
                .requestMatchers(HttpMethod.PUT, "/sinais/**").hasAnyRole("TUTOR", "INTERPRETE")
                .requestMatchers(HttpMethod.DELETE, "/sinais/**").hasAnyRole("TUTOR", "INTERPRETE")
                .requestMatchers(HttpMethod.GET, "/sinais/busca-status").authenticated()
                .requestMatchers(HttpMethod.GET, "/sinais/**").permitAll()
                .requestMatchers(HttpMethod.GET,"sinais/busca-categoria").permitAll()
                .requestMatchers(HttpMethod.POST, "/interpretes/analisar/**").permitAll()

                .requestMatchers(HttpMethod.GET, "/publicacoes/**").permitAll()
                .requestMatchers("/publicacoes/**").authenticated()


                .requestMatchers("/messages/**").hasAnyRole("TUTOR", "INTERPRETE", "PROFESSOR", "ALUNO_NAPNE")


                .requestMatchers(HttpMethod.GET, "/comentarios/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/comentarios/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/comentarios/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/comentarios/**").authenticated()

                .requestMatchers("/alunosNapne/**").hasAnyRole("ALUNO_NAPNE", "TUTOR", "INTERPRETE")


                .requestMatchers("/tutores/**", "/professores/**", "/interpretes/**", "/cursos/**",
                        "/configuracoesDeAcessibilidade/**", "/alunos/**").authenticated()
                .requestMatchers("/auth/login", "/auth/register/**", "/auth/forgot-password",
                                 "/auth/reset-password", "/auth/verify-email").permitAll()

                .requestMatchers("/auth/update-password").authenticated()
                .requestMatchers("/auth/resend").permitAll()
                .requestMatchers("/midia/upload").permitAll()
                .requestMatchers("/test").permitAll()

                .requestMatchers(
                        "/chat/**",
                        "/public/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll() // endpoints públicos
                .anyRequest().authenticated(); // resto exige autenticação

        // adiciona nosso filtro antes do filtro padrão do Spring
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // usado para hash de senhas
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}