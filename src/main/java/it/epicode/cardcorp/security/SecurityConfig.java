package it.epicode.cardcorp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwFilter jwtFilter;

    public SecurityConfig(JwFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(15);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(HttpMethod.GET,
                                "/api/carte",
                                "/api/carte/search",
                                "/api/carte/rarities"
                        ).permitAll()


                        .requestMatchers("/api/carte/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/market/**", "/orders/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.GET, "/users/*/favorites")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/users/*/favorites/*")
                        .authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/users/*/favorites/*")
                        .authenticated()

                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

