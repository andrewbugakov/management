package com.pet.project.dao.task;

import com.pet.project.model.task.TaskType;

import java.util.List;


public interface TaskTypeDao {

	List<TaskType> findAll();
	
	TaskType findByType(String type);
	
	TaskType findById(long id);
}
