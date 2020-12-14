package com.tax.service.security;

import com.tax.service.entity.Role;
import com.tax.service.entity.User;
import com.tax.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        log.info("Load user by email:{}", email);
        final User user = userService.findByEmail(email);
        final Role role = user.getRole();
        log.info("User role:{}", role);
        final SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Collections.singletonList(authority));
    }
}