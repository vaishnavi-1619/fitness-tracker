package com.fitness.userservice.serviceImpl;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.model.User;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.repository.UserRepository;
import com.fitness.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exist");
        }
      User user = new User();
      user.setEmail(request.getEmail());
      user.setFirstname(request.getFirstname());
      user.setLastname(request.getLastname());
      user.setPassword(request.getPassword());

      userRepository.save(user);

      UserResponse userResponse= new UserResponse();
      userResponse.setId(user.getId());
      userResponse.setEmail(user.getEmail());
      userResponse.setFirstname(user.getFirstname());
      userResponse.setLastname(user.getLastname());
      userResponse.setPassword(user.getPassword());
      userResponse.setCreatedAt(user.getCreatedAt());
      userResponse.setUpdatedAt(user.getUpdatedAt());

      return userResponse;

    }

    @Override
    public UserResponse getUserProfile(String userId) {
        User user= userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("Usernot found"));

        UserResponse userResponse= new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setPassword(user.getPassword());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());

        return userResponse;


    }
}
