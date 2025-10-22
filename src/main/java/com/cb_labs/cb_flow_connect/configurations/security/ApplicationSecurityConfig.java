package com.cb_labs.cb_flow_connect.configurations.security;

import com.cb_labs.cb_flow_connect.configurations.security.filters.JwtAuthenticationFilter;
import com.cb_labs.cb_flow_connect.configurations.security.filters.JwtAuthorizationFilter;
import com.cb_labs.cb_flow_connect.configurations.security.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.List;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

    @Autowired
    private AuthenticationManager userAuthManager;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAuthorizationFilter authorizationFilter;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private SecretKey secretKey;

    private static final String[] SWAGGER_ENDPOINTS = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/api/auth/**",
            "/api/test/**",
            "/authenticate",
    };

    private static final String[] PUBLIC_ENDPOINTS = {
            "/user/login",
//            "/user/info/**",
            "/jwt",
            "/auth/**"
    };

    @Bean
    public SecurityFilterChain swaggerSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(SWAGGER_ENDPOINTS);
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        JwtAuthenticationFilter userAuthenticationFilter = new JwtAuthenticationFilter(jwtConfig, secretKey);
        userAuthenticationFilter.setAuthenticationManager(userAuthManager);
        userAuthenticationFilter.setFilterProcessesUrl("/user/login");

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());
        http.exceptionHandling(config -> config.authenticationEntryPoint(authenticationEntryPoint));
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilter(userAuthenticationFilter).addFilterBefore(authorizationFilter, JwtAuthenticationFilter.class);

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(PUBLIC_ENDPOINTS).permitAll();
            auth.requestMatchers("/user").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/user/info/**").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/token/**").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/flow/**").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/bank-information/**").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/transactions/**").hasAnyRole("USER", "ADMIN");
        });

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
