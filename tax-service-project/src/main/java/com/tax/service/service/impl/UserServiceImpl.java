package com.tax.service.service.impl;

import com.tax.service.entity.User;
import com.tax.service.exception.NoDataFound;
import com.tax.service.repository.UserRepository;
import com.tax.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public User findByEmail(final String email) {
        log.info("Get user by email:{}", email);
        return userRepository.findByEmail(email).orElseThrow(NoDataFound::new);
    }
}