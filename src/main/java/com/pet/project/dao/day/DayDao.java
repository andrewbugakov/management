package com.pet.project.dao.day;

import com.pet.project.model.User;
import com.pet.project.model.day.Day;

import java.util.List;

public interface DayDao {
    Day getCurrDay(User user);
    Day findById(long id);
    void save(Day day);
    void deleteById(long id);
    List<Day> findAllDays();

}
