package com.mohanad.springpractice.security;

import com.mohanad.springpractice.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers(HttpMethod.GET, "/employees").hasRole("EMPLOYEE")

                        .requestMatchers(HttpMethod.GET, "/employees/CREATE").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/employees").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/employees/update/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/employees/update/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/employees/delete/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login.loginPage("/login").loginProcessingUrl("/processLogin").permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(exc -> exc.accessDeniedPage("/access-denied"));

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }


}
