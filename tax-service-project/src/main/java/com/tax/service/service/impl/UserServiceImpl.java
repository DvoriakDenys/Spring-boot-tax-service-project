package com.tax.service.service.impl;

import com.tax.service.dto.UserDTO;
import com.tax.service.entity.Role;
import com.tax.service.entity.User;
import com.tax.service.exception.NoDataFound;
import com.tax.service.repository.RoleRepository;
import com.tax.service.repository.UserRepository;
import com.tax.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User findByEmail(final String email) {
        log.info("Get user by email:{}", email);
        return userRepository.findByEmail(email).orElseThrow(NoDataFound::new);
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        return userRepository.save(builderUser(userDTO));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoDataFound::new);
    }

    public User builderUser (UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .lastName(userDTO.getLastName())
                .firstName(userDTO.getFirstName())
                .middleName(userDTO.getMiddleName())
                .email(userDTO.getEmail())
                .password(bCryptPasswordEncoder(userDTO.getPassword()))
                .role(findRoleByRole(Role.CLIENT))
                .build();
    }

    public String bCryptPasswordEncoder(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public Role findRoleByRole(String role){
        return roleRepository.findRoleByRole(role);
    }

}