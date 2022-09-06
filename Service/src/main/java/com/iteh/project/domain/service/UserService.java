package com.iteh.project.domain.service;

import com.iteh.project.domain.entity.Role;
import com.iteh.project.domain.entity.User;
import com.iteh.project.domain.repository.RoleRepo;
import com.iteh.project.domain.repository.UserRepo;
import com.iteh.project.infrastructure.security.JwtTokenProvider;
import com.iteh.project.payload.LoginRequest;
import com.iteh.project.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
                );
    }

    public Role createRole(Role role) {
        return roleRepo.save(role);
    }


    public Map<String, Object> authenticateUser(LoginRequest loginRequest) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate( new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    ) );
        } catch (BadCredentialsException e){
            throw new Exception( "WRONG_USERNAME_OR_PASSWORD" );
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User authenticatedUser = (User) authentication.getPrincipal();
        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", SecurityUtils.getTokenPrefix() + tokenProvider.generateAccessToken(authentication));
        response.put("refreshToken", tokenProvider.generateRefreshToken(authentication));
        return response;
    }

}