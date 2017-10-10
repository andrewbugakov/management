package com.pet.project.dao.day;

import com.pet.project.model.day.Pause;
import com.pet.project.model.day.Day;

import java.util.List;

public interface PauseDao {

    Pause getCurrPause(Day day);

    Pause findById(long id);

    void save(Pause pause);

    void deleteById(long id);

    List<Pause> findAllPause();
}
