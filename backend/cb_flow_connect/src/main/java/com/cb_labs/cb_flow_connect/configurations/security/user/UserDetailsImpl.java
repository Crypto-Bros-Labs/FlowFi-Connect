package com.cb_labs.cb_flow_connect.configurations.security.user;

import com.cb_labs.cb_flow_connect.persistance.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;
    private final String authCode;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.<GrantedAuthority>of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
    }

    @Override
    public String getPassword() {
        return user.getEmail();
    }

    @Override
    public String getUsername() {
        return authCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsNonBlocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return user.getEmail();
    }
}
