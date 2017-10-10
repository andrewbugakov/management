package com.pet.project.service;

import com.pet.project.model.User;
import com.pet.project.model.day.Day;
import com.pet.project.model.task.Task;
import com.pet.project.model.meeting.Meeting;

import java.util.List;

public interface UserService {
	User findById(int id);

	User findBySSO(String sso);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserBySSO(String sso);

	List<User> findAllUsers();

	boolean isUserSSOUnique(Integer id, String sso);

	List<User> findByFirstNameOrLastName(String firstOrLastName);

	List<User> findSubordinates(String sso);

	void addToSubordinates(String sso_subr, String sso_subte);

	List<Task> getAllTasksCreatedByEmp(String sso);

	List<Task> getAllTaskForEmp(String sso);

	List<Meeting> getAllMeetings(String sso);

	List<Task> getOutTasks(String principal);


	List<Task> getEndedTasks(String principal);

	List<Task> getSovmTasks(String principal);

	List<Day> getDays(String principal);

	/*User findById(int id);
	
	User findBySSO(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);
*/
}