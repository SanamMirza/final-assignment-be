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
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/users").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/accounts").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/accounts", "/products").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/accounts").hasAuthority("USER")
//                .antMatchers(HttpMethod.PUT, "/accounts").hasAuthority("USER")

                .antMatchers(HttpMethod.POST, "/appointments", "/products").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/appointments/**", "/products/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/appointments/**", "/products/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/appointments").permitAll()

                .antMatchers(HttpMethod.POST, "/filuploads").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/fileuploads").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/fileuploads/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/filuploads").hasAuthority("ADMIN")

                .antMatchers("/users", "/appointments", "/products", "/fileuploads").hasAnyAuthority("USER", "ADMIN")

                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}



