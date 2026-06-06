package com.fitness.activityservice.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final WebClient userServiceWebClient;

    public Boolean validateUser(String userId){
        log.info("calling user service for {]", userId);
        return userServiceWebClient.get()
                .uri("/api/users/{userId}/validate",userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

    }


}
