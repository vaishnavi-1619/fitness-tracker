package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;

public interface UserService {
    UserResponse register(RegisterRequest request);

    UserResponse getUserProfile(String userId);

    Boolean existByUserId(String userId);
}
