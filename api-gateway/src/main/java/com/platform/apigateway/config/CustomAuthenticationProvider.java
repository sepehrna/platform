package com.platform.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private YourTokenService tokenService;  // your service to call the token endpoint

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        TokenResponse tokenResponse = tokenService.getTokensByUsernameAndPassword(username, password);

        if (tokenResponse != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else {
            throw new BadCredentialsException("Authentication failed for " + username);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
