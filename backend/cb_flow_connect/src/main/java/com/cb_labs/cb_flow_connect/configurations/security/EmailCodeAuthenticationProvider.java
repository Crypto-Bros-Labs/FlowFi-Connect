package com.cb_labs.cb_flow_connect.configurations.security;

import com.cb_labs.cb_flow_connect.configurations.security.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class EmailCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String code = authentication.getCredentials().toString();

        try {
            UserDetailsImpl details = (UserDetailsImpl) userDetailsService.loadUserByUsername(code);

            if (!details.getName().equals(email)) {
                throw new BadCredentialsException("Invalid credential - Full authentication is required");
            }

            return new UsernamePasswordAuthenticationToken(
                details, null, details.getAuthorities()
            );
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid credentials: " + ex.getLocalizedMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
