//package com.platform.apigateway.config;
//
//import com.platform.apigateway.domain.ApplicationUserService;
//import com.platform.apigateway.domain.entities.ApplicationUserDetail;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.RememberMeAuthenticationToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//
//    private final ApplicationUserService applicationUserService;
//
//    @Autowired
//    public CustomAuthenticationProvider(ApplicationUsersConfig usersConfig) {
//        this.applicationUserService = usersConfig.getApplicationUserService();
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String token = authentication.getCredentials().toString();
//
//        ApplicationUserDetail verifiedUser = applicationUserService.verifyUser(token);
//
//        if (verifiedUser != null) {
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            return new RememberMeAuthenticationToken("Bearer ", token, authorities);
//        } else {
//            throw new BadCredentialsException("Authentication failed for token: \n" + token);
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
