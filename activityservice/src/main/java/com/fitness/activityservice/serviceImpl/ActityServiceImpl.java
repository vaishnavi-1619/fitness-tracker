package com.fitness.activityservice.serviceImpl;


import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import com.fitness.activityservice.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;

    @Override
    public ActivityResponse trackActivity(ActivityRequest request) {

        boolean isValidUser= userValidationService.validateUser(request.getUserId());

        if(!isValidUser){
            throw new RuntimeException("Invalid user: "+ request.getUserId());
        }
        Activity activity= Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .caloriesBurned((request.getCaloriesBurned()))
                .duration(request.getDuration())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity= activityRepository.save(activity);

        return mapToResponse(savedActivity);




    }

    @Override
    public ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse activityResponse= new ActivityResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setType(activity.getType());
        activityResponse.setDuration(activity.getDuration());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
        activityResponse.setCreatedAt(activity.getCreatedAt());
        activityResponse.setUpdatedAt(activity.getUpdatedAt());
        activityResponse.setUserId(activity.getUserId());
        activityResponse.setAdditionalMetrics(activity.getAdditionalMetrics());

        return  activityResponse;


    }
}
