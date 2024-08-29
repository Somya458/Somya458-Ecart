/*
 * package com.notification.service.app.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.security.oauth2.jwt.JwtDecoder; import
 * org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig {
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception { http.authorizeRequests(authorizeRequests ->
 * authorizeRequests.anyRequest().authenticated()) .oauth2ResourceServer(
 * oauth2ResourceServer -> oauth2ResourceServer.jwt(jwt ->
 * jwt.decoder(jwtDecoder()))); return http.build(); }
 * 
 * @Bean public JwtDecoder jwtDecoder() { return
 * NimbusJwtDecoder.withJwkSetUri("http://auth-server/.well-known/jwks.json").
 * build(); } }
 */