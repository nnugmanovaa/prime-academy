package com.example.pmcourse.service.impl;

import com.example.pmcourse.exception.ErrorMessageConstants;
import com.example.pmcourse.exception.GeneralApiServerException;
import com.example.pmcourse.model.dto.UserCreateDto;
import com.example.pmcourse.model.entities.User;
import com.example.pmcourse.repository.UserRepository;
import com.example.pmcourse.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserCreateDto dto) {
        Optional<User> optionalUser = userRepository.findByLogin(dto.getLogin());
        if (optionalUser.isPresent()) {
            throw new GeneralApiServerException(ErrorMessageConstants.USER_ALREADY_EXISTS);
        }
        return userRepository.save(new User(dto.getLogin(), passwordEncoder.encode(dto.getPassword())));
    }


    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return userRepository.findByLogin(principal.getUsername()).orElseThrow(
                () -> new IllegalArgumentException(ErrorMessageConstants.USER_NOT_FOUND)
        );
    }

    @Override
    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
