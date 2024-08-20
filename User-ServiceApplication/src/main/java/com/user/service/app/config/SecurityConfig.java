/*
 * package com.user.service.app.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.web.SecurityFilterChain; import
 * org.springframework.security.web.authentication.
 * UsernamePasswordAuthenticationFilter;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig {
 * 
 * @Bean public AuthenticationManager authenticationManager(HttpSecurity http,
 * PasswordEncoder passwordEncoder, UserDetailsService userDetailsService)
 * throws Exception { return
 * http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(
 * userDetailsService) .passwordEncoder(passwordEncoder).and().build(); }
 * 
 * 
 * @Bean public BCryptPasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception {
 * http.csrf().disable().authorizeRequests().requestMatchers("/register",
 * "/authenticate-user/**","/h2-console/**").permitAll().anyRequest()
 * .authenticated().and().sessionManagement().sessionCreationPolicy(
 * SessionCreationPolicy.STATELESS); http.headers(headers ->
 * headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
 * http.csrf().disable(); http.httpBasic(); return http.build(); } }
 */
