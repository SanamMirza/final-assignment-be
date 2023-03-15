package com.example.finalassignment.security;

import com.example.finalassignment.filter.JwtRequestFilter;
import com.example.finalassignment.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SpringSecurityConfig {
    public final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;
    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/users/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/**").permitAll()

                .antMatchers(HttpMethod.POST, "/accounts/**").permitAll()
                .antMatchers(HttpMethod.GET, "/accounts/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/accounts/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/accounts/**").permitAll()

                .antMatchers(HttpMethod.POST, "/appointments/**").permitAll()
                .antMatchers(HttpMethod.GET, "/appointments/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/appointments/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/appointments/**").permitAll()

                .antMatchers(HttpMethod.POST, "/products/**").permitAll()
                .antMatchers(HttpMethod.GET, "/products/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/products/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/products/**").permitAll()

                .antMatchers(HttpMethod.POST, "/docs/**").permitAll()
                .antMatchers(HttpMethod.GET, "/docs/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/docs/**").permitAll()


                .antMatchers(HttpMethod.POST, "/contact").permitAll()
                .antMatchers(HttpMethod.GET, "/contact/**").permitAll()

                .antMatchers("/users", "/accounts", "/appointments", "/products", "/docs", "/contact").hasAnyAuthority("USER", "ADMIN")

                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/**").denyAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}



