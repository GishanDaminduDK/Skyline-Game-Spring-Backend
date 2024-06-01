package com.skyline.sdc_project.config;

import com.skyline.sdc_project.service.PlayerLoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final PlayerLoginService playerService;

    public SecurityConfig(PlayerLoginService playerService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.playerService = playerService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/v1/player/log").permitAll()
                    .requestMatchers("/search").hasAnyAuthority("ADMIN", "MANAGER")
                    .requestMatchers(HttpMethod.POST, "/api/v1/player/savePlayer").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/player/saveAnswers").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/player/send_credentials").permitAll()
                    .anyRequest().permitAll();
        });
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5500", "http://127.0.0.1:5500", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true); // if you need to include credentials like cookies, authorization headers, or TLS client certificates
        configuration.setExposedHeaders(Arrays.asList("Authorization")); // if you expose custom headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
