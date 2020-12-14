package com.tax.service.service;

import com.tax.service.dto.UserDTO;
import com.tax.service.entity.User;

public interface UserService {
    User findByEmail(final String email);
    User saveUser (UserDTO userDTO);
}
