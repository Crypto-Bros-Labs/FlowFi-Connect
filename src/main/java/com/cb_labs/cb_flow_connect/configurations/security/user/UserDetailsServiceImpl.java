package com.cb_labs.cb_flow_connect.configurations.security.user;

import com.cb_labs.cb_flow_connect.persistance.entities.User;
import com.cb_labs.cb_flow_connect.service.IAuthCodeService;
import com.cb_labs.cb_flow_connect.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthCodeService authCodeService;

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        User user = userService.getUserByValidAuthCode(code);
        authCodeService.validateAndDeleteCode(code);

        return new UserDetailsImpl(user, code);
    }
}
