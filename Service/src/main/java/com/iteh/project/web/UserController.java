package com.iteh.project.web;

import com.iteh.project.domain.entity.User;
import com.iteh.project.domain.service.UserService;
import com.iteh.project.infrastructure.security.JwtTokenProvider;
import com.iteh.project.payload.JWTLoginSuccessResponse;
import com.iteh.project.payload.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result,
                                   @RequestHeader(value = "locale", required = false) String locale) throws Exception {
        Authentication authentication = (Authentication) userService.authenticateUser(loginRequest);

        User userForLogin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Validate.isNotNull(userForLogin, DescriptionUtil.getErrorDescription("USER_IS_NULL"));
        List<String> tokenList = tokenProvider.getTokens(authentication);
        return ResponseEntity.ok(getTokensFromTokenList(tokenList));
    }

    private JWTLoginSuccessResponse getTokensFromTokenList(List<String> tokenList) {
        String accessToken = tokenList.get(0);
        String refreshToken = tokenList.get(1);

        return new JWTLoginSuccessResponse(true, accessToken, refreshToken);
    }


}
