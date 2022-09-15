package com.iteh.project.infrastructure.security;

import com.iteh.project.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.http.HttpMethod.*;


@EnableWebMvc
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .headers().frameOptions().disable();
        http
                .authorizeRequests()
                .antMatchers("/api/login/**","/actuator/**","/h2-console/**","/swagger-ui/**","/v3/api-docs/**").permitAll()
                .antMatchers("/api/prijava-ispita/update").hasAuthority("ROLE_PROFA")
                .antMatchers(GET, "/api/prijava-ispita").hasAuthority("ROLE_PROFA")
                .antMatchers(GET, "/api/prijava-ispita/student").hasAuthority("ROLE_STUDENT")
                .antMatchers(POST, "/api/prijava-ispita").hasAuthority("ROLE_STUDENT")
                .antMatchers(DELETE, "/api/prijava-ispita/**").hasAuthority("ROLE_STUDENT")
                .antMatchers(POST, "/api/prijava-predmeta/**").hasAuthority("ROLE_STUDENT")
                .anyRequest().authenticated();

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
