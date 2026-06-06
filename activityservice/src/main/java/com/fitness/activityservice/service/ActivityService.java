package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;

public interface ActivityService {
    public ActivityResponse trackActivity(ActivityRequest request);

    public ActivityResponse mapToResponse(Activity activity);
}
