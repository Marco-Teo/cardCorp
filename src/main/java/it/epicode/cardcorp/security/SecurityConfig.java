package it.epicode.cardcorp.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final JwFilter jwtFilter;

    public SecurityConfig(JwFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // 1) Bean per la politica CORS: ORIGIN frontend + metodi e headers necessari
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        // 1a) metti l'URL del tuo front (es: http://localhost:3000)
        cors.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        cors.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        cors.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));
        cors.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

    // 2) SecurityFilterChain: monta prima il CORS, poi il JWT, poi le regole
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // disabilita CSRF e usa la nostra configurazione CORS
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())

                // monta il filtro JWT **dopo** il CORS
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                // regole di accesso
                .authorizeHttpRequests(auth -> auth
                        // sempre consenti tutti gli OPTIONS (preflight)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // le GET libere:
                        .requestMatchers(HttpMethod.GET,
                                "/api/carte",
                                "/api/carte/search",
                                "/api/carte/rarities"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/carte/search").permitAll()

                        // tutto /api/carte/** (POST, DELETE, PUT) riservato ad ADMIN
                        .requestMatchers("/api/carte/**").hasRole("ADMIN")

                        // creare ordine richiede login
                        .requestMatchers(HttpMethod.POST, "/ordini/**").authenticated()

                        // gestire i preferiti richiede login
                        .requestMatchers("/users/*/favorites/**").authenticated()

                        // tutto il resto è pubblico
                        .anyRequest().permitAll()
                )

                // fallback basic auth (non usato dai client JWT, ma serve per Swagger/H2 console...)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // 3) Password encoder per Spring Security
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(15);
    }
}
