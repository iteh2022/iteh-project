package com.iteh.project.infrastructure.security;

import com.iteh.project.domain.entity.User;
import com.iteh.project.util.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class JwtTokenProvider {


    private String SECRET = SecurityUtils.getSecret();
    private String TOKEN_PREFIX = SecurityUtils.getTokenPrefix();
    private Long EXPIRATION_TIME = SecurityUtils.getAccessTokenExptime();
    private Long EXPIRATION_TIME_REFRESH_TOKEN = SecurityUtils.getRefreshTokenExpTime();

    public String generateRefreshToken(Authentication authentication) {
        return generateToken(authentication, 300000);
    }

    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication, EXPIRATION_TIME);
    }

    private String generateToken(Authentication authentication, long expirationTime) {

        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + expirationTime);

        String userId = Long.toString(user.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());

        Long loggedEmployeeId = user.getId();
        claims.put("loggedEmployeeId", loggedEmployeeId);

        return null;
//        return Jwts.builder().setSubject(userId).setClaims(claims)
//                .setIssuedAt(now).setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }


    public List<String> getTokens(Authentication authentication){
        String accessToken = TOKEN_PREFIX + generateAccessToken(authentication);
        String refreshToken = generateRefreshToken(authentication);
        List<String> tokenList = new ArrayList<>();
        tokenList.add(0,accessToken);
        tokenList.add(1,refreshToken);
        return tokenList;
    }

}

