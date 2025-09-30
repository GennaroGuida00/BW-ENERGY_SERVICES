package Team3_BW.energy_services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity// serve per impostare AUTORIZZAZIONE sui singoli endpoint tramite @PreAuthorize!!
@EnableWebSecurity// Annotazione che serve per stabilire che questa sarà una classe di configurazione per configurare Spring Security

public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Per poter sovrascrivere i comportamenti di default di Spring Security devo utilizzare questo Bean, il quale mi consentirà di:
        // - disabilitare i comportamenti di default che non mi interessano

        httpSecurity.formLogin(f -> f.disable());// Non voglio il form di login
        httpSecurity.csrf(c -> c.disable());// Disabilito la protezione da CSRF
        httpSecurity.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // Non vogliamo utilizzare le sessioni perché JWT non utilizza le sessioni (STATELESS)
        httpSecurity.authorizeHttpRequests(h -> h.requestMatchers("/**").permitAll());
        // Disabilitiamo i vari errori 401/403 che riceviamo di default andando a sproteggere ogni endpoint. Lo facciamo perché andremo
        // ad implementare un meccanismo di autenticazione custom, per il quale stabiliremo noi su quali endpoint intervenire
        httpSecurity.cors(Customizer.withDefaults()); // Abilita CORS secondo configurazione WebConfig
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder getBCrypt() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173/", "https://www.myfuturefrontendforthisproject.com"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
