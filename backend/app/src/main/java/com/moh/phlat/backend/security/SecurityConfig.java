package com.moh.phlat.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwt-set-uri}")
    String jwkSetUri;

    private final JwtAuthConverter jwtAuthConverter;


    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return getFilterChain(http);
    }

    private SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
        http.csrf(Customizer.withDefaults())
            //only check token validity here. Role checks on controller methods
            .authorizeHttpRequests((authRequestMatcher) -> authRequestMatcher.anyRequest().authenticated())
            .oauth2ResourceServer((oauth2Resourceserver) -> oauth2Resourceserver.jwt(
                    (jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                                                   .jwtAuthenticationConverter(jwtAuthConverter))));

        return http.build();
    }


    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }


}
