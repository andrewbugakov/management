package com.pet.project.dao;

import com.pet.project.model.task.Task;
import com.pet.project.model.User;
import com.pet.project.model.day.Day;
import com.pet.project.model.meeting.Meeting;

import java.util.List;

public interface UserDao {

	User findById(int id);

	User findBySSO(String sso);

	List<User> findByFirstNameOrLastName(String firstOrLastName);

	List<User> findSubordinates(String sso);

	void addToSubordinates(String sso_subr, String sso_subte);

	List<Task> getAllTasksCreatedByEmp(String sso);

	List<Task> getAllTaskForEmp(String sso);
	

	List<Meeting> getAllMeetings(String sso);

	void save(User user);

	void deleteBySSO(String sso);

	List<User> findAllUsers();

	List<Task> getOutTasksByEmp(String principal);

	List<Task> getEndedTasksByEmp(String principal);

	List<Task> getSovmTasks(String principal);

	List<Day> getDays(String principal);
}

