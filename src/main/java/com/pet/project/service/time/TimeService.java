package com.pet.project.service.time;

import com.pet.project.model.time.WorkingTime;

public interface TimeService {
    WorkingTime getTimeModel();

    void save(WorkingTime workingTime);
}
