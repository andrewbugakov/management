package com.pet.project.dao.time;

import com.pet.project.model.time.WorkingTime;

public interface WorkingTimeDao {
    WorkingTime getWorkingTime();
    void save(WorkingTime workingTime);
}
