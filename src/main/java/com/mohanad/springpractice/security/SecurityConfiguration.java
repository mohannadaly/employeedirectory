package com.mohanad.springpractice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {
    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED FROM USERS WHERE USERNAME=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT A.USERNAME, R.AUTHORITY FROM AUTHORITIES as A, ROLES AS R WHERE A.ROLE = R.ROLE AND A.USERNAME=?");
        return userDetailsManager;
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
