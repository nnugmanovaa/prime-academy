package com.example.pmcourse.service;

import com.example.pmcourse.model.dto.UserCreateDto;
import com.example.pmcourse.model.entities.User;

public interface IUserService {
    User createUser(UserCreateDto dto);

    User getCurrentUser();

    Long getCurrentUserId();
}
