package com.pet.project.service.task;

import com.pet.project.model.task.Task;

import java.util.List;

public interface TaskService {
    Task findById(long id);
    List<Task> findAll();
    void save(Task task);
    String startstopday(long id);
    void deleteById(long id);
    List<Task> findByID(int id);
    List<Task> findByIdDay(long id);
    List<Task> findOutOfDeadlineTasks(long id);
    List<Task> findClosedTasks(long id);
}
