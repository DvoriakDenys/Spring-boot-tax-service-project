package com.tax.service.service;

import com.tax.service.entity.User;

public interface UserService {
    User findByEmail(final String email);
}
