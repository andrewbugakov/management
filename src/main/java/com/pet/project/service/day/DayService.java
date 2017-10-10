package com.pet.project.service.day;

import com.pet.project.model.task.Task;
import com.pet.project.model.User;
import com.pet.project.model.day.Day;
import com.pet.project.model.day.Pause;

import java.util.List;

public interface DayService {

    Day getCurrDay(User user);
    void startDay(User user);
    void stopDay(User user);
    Pause getCurrPause(User user);
    void startPause(User user);
    void addCurPauseDesc(User user,String desc);
    void stopPause(User user);
    void save(Pause pause);
    void save(Day day);
    void update(User user,Task task);
    void updateDay(User user, String desc, List<Integer> tasks);
}