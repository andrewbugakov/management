package com.pet.project.dao.task;


import com.pet.project.model.task.Task;

import java.util.List;

public interface TaskDao {
    Task findById(long id);
    List<Task> findAll();
    List<Task> findAllById(int id_emp);
    List<Task> findAllByIdDay(long id);
    void save(Task task);
    void deleteById(long id);
}
