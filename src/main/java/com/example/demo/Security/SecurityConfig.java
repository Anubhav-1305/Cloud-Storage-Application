package com.example.demo.Security;

import com.example.demo.Service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(form->form.loginPage("/login").permitAll())
                .formLogin(form->form.defaultSuccessUrl("/home" , true).permitAll())
                .logout((logout) -> logout.logoutUrl("/logout"))
                .logout((logout) -> logout.logoutSuccessUrl("logout-success"))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/signup").permitAll()
                        .anyRequest().authenticated())
                .build();

    }

}