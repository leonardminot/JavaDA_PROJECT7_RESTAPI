package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for defining security settings in the application.
 * There are two authentication ways: the first one for the rest api,
 * the second one for the web interface.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomUserDetailService customUserDetailService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    public SecurityConfiguration(CustomUserDetailService customUserDetailService, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.customUserDetailService = customUserDetailService;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    /**
     * Configure the security filter chain for the Rest API part of the application.
     * <strong>STATELESS</strong> authentication.
     * <p>
     *     <strong>CSRF</strong>: The CSRF is disabled
     * </p>
     * <p>
     *     <strong>URL</strong>: All URLs with /user and /secure require an ADMIN role in the application.
     *    All other requests must be authenticated
     * </p>
     * <p>
     *     <strong>SESSION MANAGEMENT</strong>: stateless authentication. Credentials must be provided for every requests.
     * </p>
     * <p>
     *     <strong>HTTP BASICS</strong>: default authentication
     * </p>
     *
     * @param http The {@link HttpSecurity}  object used to configure security for HTTP requests (managed by SpringSecurity).
     *      * @return A {@link SecurityFilterChain} instance representing the configured security filter chain.
     *      * @throws Exception If an error occurs during configuration.
     */
    @Bean
    @Order(1)
    SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.securityMatcher("/api/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/user/**").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }


    /**
     * Configure the security filter chain for the application.
     * <p>
     *     <strong>URL</strong>: All URLs with /user and /secure require an ADMIN role in the application.
     *     All other requests must be authenticated
     * </p>
     * <p>
     *     <strong>FORM LOGIN</strong>: use the default layout provided by SPRING SECURITY.
     * </p>
     * <p>
     *     <strong>LOGOUT</strong>: Configure default logout process.
     *     Successful logout redirected in /login page.
     * </p>
     * <p>
     *     <strong>EXCEPTION HANDLING</strong>: Customize accessDeniedHandler to manage 403 pages error.
     * </p>
     *
     * @param http The {@link HttpSecurity}  object used to configure security for HTTP requests (managed by SpringSecurity).
     * @return A {@link SecurityFilterChain} instance representing the configured security filter chain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/user/**").hasRole("ADMIN");
                    auth.requestMatchers("/secure/**").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                }).formLogin(Customizer.withDefaults())
                .logout(logout -> {
                    logout.logoutUrl("/logout");
                    logout.logoutSuccessUrl("/login");
                })
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(customAccessDeniedHandler);
                })
                .build();
    }

    /**
     * Configures and returns an {@link AuthenticationManager} instance for authentication within the application.
     * This method is responsible for configuring the authentication manager to utilize a custom {@code UserDetailsService}
     * linked to the application database.
     *
     * @param http The {@link HttpSecurity} object used to configure security for HTTP requests (managed by Spring Security).
     * @param bCryptPasswordEncoder The {@link BCryptPasswordEncoder} used for password encoding.
     * @return An {@link AuthenticationManager} instance configured with the specified user details service and password encoder.
     * @throws Exception If an error occurs during authentication manager configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    /**
     * Provides a {@link BCryptPasswordEncoder} instance for password encoding.
     *
     * @return A {@link BCryptPasswordEncoder} instance.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
