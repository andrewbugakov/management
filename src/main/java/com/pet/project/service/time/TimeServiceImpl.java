package com.pet.project.service.time;

import com.pet.project.dao.time.WorkingTimeDao;
import com.pet.project.model.time.WorkingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("timeService")
@Transactional
public class TimeServiceImpl implements TimeService {
    @Autowired
    WorkingTimeDao workingTimeDao;

    @Override
    public WorkingTime getTimeModel() {
        return workingTimeDao.getWorkingTime();
    }

    @Override
    public void save(WorkingTime workingTime) {
        workingTimeDao.save(workingTime);
    }
}
