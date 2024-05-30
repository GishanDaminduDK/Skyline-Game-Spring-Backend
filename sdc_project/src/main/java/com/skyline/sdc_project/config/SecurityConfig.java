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
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
        System.out.println("SecurityFilterChain");
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.addAllowedOrigin("http://localhost:5173");
            corsConfig.addAllowedMethod("*");
            corsConfig.addAllowedHeader("*");
            corsConfig.setAllowCredentials(false);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfig);
            cors.configurationSource(source);
        });

        http.authorizeHttpRequests(request -> {
                    request.requestMatchers("/api/v1/player/log").permitAll()
                            .requestMatchers("/search").hasAnyAuthority("ADMIN", "MANAGER")
                            .requestMatchers(HttpMethod.POST, "/api/v1/player/savePlayer").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/player/saveAnswers").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/player/send_credentials").permitAll()
                            .anyRequest().permitAll();
                })
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}