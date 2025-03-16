package com.ce.um.gestion_usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{

        http
        .authorizeHttpRequests(customizeRequests -> {
            customizeRequests
                    .requestMatchers(HttpMethod.GET,"/api/vi/*").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/vi/*").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated();
            }
            )
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public UserDetailsService memoryUsers(){
        UserDetails admin = User.builder()
                            .username("admin")
                            .password(passwordEncoder().encode("admin"))
                            .roles("ADMIN")
                            .build();

        UserDetails customer = User.builder()
        .username("customer")
        .password(passwordEncoder().encode("customer123"))
        .roles("CUSTOMER")
        .build();

        return new InMemoryUserDetailsManager(admin, customer);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
