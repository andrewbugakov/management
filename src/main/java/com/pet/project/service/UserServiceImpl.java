package com.pet.project.service;

import com.pet.project.dao.UserDao;
import com.pet.project.dao.office_structure.BranchOfficeDao;
import com.pet.project.dao.office_structure.DepartmentDao;
import com.pet.project.model.User;
import com.pet.project.model.day.Day;
import com.pet.project.model.task.Task;
import com.pet.project.model.meeting.Meeting;
import com.pet.project.model.office_structure.BranchOffice;
import com.pet.project.model.office_structure.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private BranchOfficeDao branchOfficeDao;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySSO(String sso) {
		User user = dao.findBySSO(sso);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(user.getDept_where_head()!=null){
			Department department=departmentDao.findById(user.getDept_where_head().getId_department());
			user.setDept_where_head(department);
			department.setManager(user);
			department.setManagerid(user.getId());

		}
		if(user.getBranch_where_head()!=null){
			BranchOffice branchOffice=branchOfficeDao.findById(user.getBranch_where_head().getId_branch());
			user.setBranch_where_head(branchOffice);
			branchOffice.setManager(user);
			branchOffice.setManagerid(user.getId());
		}

		dao.save(user);
		if(user.getDept_where_head()!=null) {
			user.getDept_where_head().setManagerid(user.getId());
			departmentDao.save(user.getDept_where_head());
			System.out.println("Department:{}"+user.getDept_where_head());
		}
		if(user.getBranch_where_head()!=null){
			user.getBranch_where_head().setManagerid(user.getId());
			branchOfficeDao.save(user.getBranch_where_head());
			System.out.println("BranchOffice:{}"+user.getBranch_where_head());

		}
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById((int) user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
			entity.setBirthday(user.getBirthday());
			entity.setDepartment(user.getDepartment());
			entity.setInteroffice_phone(user.getInteroffice_phone());
			entity.setPatronymic(user.getPatronymic());
			entity.setWork_phone(user.getWork_phone());
			entity.setPosition(user.getPosition());
			if(user.getDept_where_head()!=null){
				Department department=departmentDao.findById(user.getDept_where_head().getId_department());
				user.setDept_where_head(department);
				department.setManager(user);
				department.setManagerid(user.getId());

			}
			if(user.getBranch_where_head()!=null){
				BranchOffice branchOffice=branchOfficeDao.findById(user.getBranch_where_head().getId_branch());
				user.setBranch_where_head(branchOffice);
				branchOffice.setManager(user);
				branchOffice.setManagerid(user.getId());
			}
			if(user.getDept_where_head()!=null) {
				user.getDept_where_head().setManagerid(user.getId());
				departmentDao.save(user.getDept_where_head());
				System.out.println("Department:{}"+user.getDept_where_head());
			}
			if(user.getBranch_where_head()!=null){
				user.getBranch_where_head().setManagerid(user.getId());
				branchOfficeDao.save(user.getBranch_where_head());
				System.out.println("BranchOffice:{}"+user.getBranch_where_head());

			}
			dao.save(entity);
		}
//		if(!user.getPassword().equals(entity.getPassword())){
//			user.setPassword(passwordEncoder.encode(user.getPassword()));
//			dao.save(entity);
//		}

	}

	
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public List<User> findByFirstNameOrLastName(String firstOrLastName) {
		return dao.findByFirstNameOrLastName(firstOrLastName);
	}

	@Override
	public List<User> findSubordinates(String sso) {
		return dao.findSubordinates(sso);
	}

	@Override
	public void addToSubordinates(String sso_subr, String sso_subte) {
		dao.addToSubordinates(sso_subr,sso_subte);
	}

	@Override
	public List<Task> getAllTasksCreatedByEmp(String sso) {
		return dao.getAllTasksCreatedByEmp(sso);
	}

	@Override
	public List<Task> getAllTaskForEmp(String sso) {
		return dao.getAllTaskForEmp(sso);
	}

	@Override
	public List<Meeting> getAllMeetings(String sso) {
		return dao.getAllMeetings(sso);
	}

	@Override
	public List<Task> getOutTasks(String principal) {
		return dao.getOutTasksByEmp(principal);
	}

	@Override
	public List<Task> getEndedTasks(String principal) {
		return dao.getEndedTasksByEmp(principal);
	}

	@Override
	public List<Task> getSovmTasks(String principal) {
		return dao.getSovmTasks(principal);
	}

	@Override
	public List<Day> getDays(String principal) {
		return dao.getDays(principal);
	}


}
