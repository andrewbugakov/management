package com.pet.project.dao;

import com.pet.project.model.User;
import com.pet.project.model.day.Day;
import com.pet.project.model.meeting.Meeting;
import com.pet.project.model.task.Task;
import com.pet.project.model.office_structure.BranchOffice;
import com.pet.project.model.office_structure.Department;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public List getLates(int id){
		Map<String,Integer> map=new HashMap<>();
		List list=getSession().createSQLQuery("select date,id,(starttime-'8:00')late from day where starttime>'8:00'").list();
		return list;
	}

	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
			Hibernate.initialize(user.getMeetings());
		}
		return user;
	}

	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getDays());
			Hibernate.initialize(user.getUserProfiles());
			Hibernate.initialize(user.getMeetings());

		}
		return user;
	}

	@Override
	public List<User> findByFirstNameOrLastName(String firstOrLastName) {
		logger.info("firstOrLastName : {}", firstOrLastName);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.or(Restrictions.like("firstName", "%"+firstOrLastName+"%"),Restrictions.like("lastName", "%"+firstOrLastName+"%"),Restrictions.like("patronymic", "%"+firstOrLastName+"%")));
		List<User> users = crit.list();
		System.out.println(users.size());
		return users;
	}

	@Override
	public List<User> findSubordinates(String sso) {

		User user=findBySSO(sso);
		List<BranchOffice> branchOffices=getSession().createCriteria(BranchOffice.class).add(Restrictions.eq("managerid",user.getId())).list();
		List<Department> departments=getSession().createCriteria(Department.class).add(Restrictions.eq("managerid",user.getId())).list();
		Set<User> users=new HashSet<>();
		for(BranchOffice branch:branchOffices){
			for(Department dept:branch.getDepartments()){
				Hibernate.initialize(dept.getEmployees());
				users.addAll(dept.getEmployees());
			}
		}
		for(Department dept:departments){
			Hibernate.initialize(dept.getEmployees());
			users.addAll(dept.getEmployees());
		}
		System.out.println(users.size());
		List<User> users1=new ArrayList<>();
		users1.addAll(users);
		return users1;
//		if(user!=null){
//			Hibernate.initialize(user.getSubordinate());
//		}
//		System.err.println(user.getDept_where_head()==null?00:user.getDept_where_head().getTitle());
//		ArrayList<User> arrayList=new ArrayList<>();
//		arrayList.addAll(user.getSubordinate());
//		return arrayList;
	}

	@Override
	public void addToSubordinates(String ssor, String ssote) {
		User subordinator=findBySSO(ssor);
		User subordinate=findBySSO(ssote);
		subordinator.getSubordinate().add(subordinate);
		subordinate.setSubordinator(subordinator);
		update(subordinate);
		update(subordinator);
	}

	@Override
	public List<Task> getAllTasksCreatedByEmp(String sso) {
		User user = findBySSO(sso);
		if (user != null) {
			Hibernate.initialize(user.getTaskCreatedBy());
		}
		ArrayList<Task> arrayList = new ArrayList<>();
		arrayList.addAll(user.getTaskCreatedBy());
		return arrayList;
	}
/*
select * from task where id_user_who_do=1 and actualend is not null;zak
select * from task where id_user_who_do=1 and actualend is null;nezak
select * from task where id_user_who_do=1 and deadline < 'today'::timestamp ;
 */
	@Override
	public List<Task> getAllTaskForEmp(String sso) {
//		User user=findBySSO(sso);
//		if(user!=null){
//			Hibernate.initialize(user.getTaskCreatedFor());
//		}
//		System.out.println(user);
//		System.out.println(user.getTaskCreatedFor()==null?"null":user.getTaskCreatedFor().size());
//		ArrayList<Task> arrayList=new ArrayList<>();
//		arrayList.addAll(user.getTaskCreatedFor());
//		return arrayList;
		User user=findBySSO(sso);
		List<Task> tasks=getSession()
				.createCriteria(Task.class).add(Restrictions.and(Restrictions.eq("userWhoDo",user),Restrictions.isNull("actualEnd"))).list();
		return tasks;
//		List list=getSession().createSQLQuery("select * from task where id_user_who_do="+findBySSO(sso)+" and actualend is null").list();
//		return list;
	}

	@Override
	public List<Meeting> getAllMeetings(String sso) {
		User user=findBySSO(sso);
		List<Meeting> meetings=getSession()
				.createCriteria(Meeting.class)
				.createAlias("employees", "employee")
				.add(Restrictions.eq("employee.id", user.getId())).list();
		return meetings;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}

		return users;
	}

	@Override
	public List<Task> getOutTasksByEmp(String principal) {
		User user=findBySSO(principal);
		List<Task> tasks=getSession()
				.createCriteria(Task.class).add(Restrictions.and(Restrictions.eq("userWhoDo",user),Restrictions.isNull("actualEnd"),Restrictions.lt("deadline",new Date()))).list();
		return tasks;
//		List list=getSession().createSQLQuery("select * from task where id_user_who_do="+findBySSO(principal).getId()+" and deadline < 'today'::timestamp ;").list();
//		return list;
	}

	@Override
	public List<Task> getEndedTasksByEmp(String principal) {
		User user=findBySSO(principal);
		List<Task> tasks=getSession()
				.createCriteria(Task.class).add(Restrictions.and(Restrictions.eq("userWhoDo",user),Restrictions.isNotNull("actualEnd"))).list();
		return tasks;
	}

	@Override
	public List<Task> getSovmTasks(String principal) {
		User user=findBySSO(principal);
		if(user!=null)
			Hibernate.initialize(user.getColtasks());
		List<Task> tasks=new ArrayList<>();
		tasks.addAll(user.getColtasks());
		return tasks;
	}

	@Override
	public List<Day> getDays(String principal) {
		User user=findBySSO(principal);
		if (user!=null)
			Hibernate.initialize(user.getDays());

		List<Day> days=new ArrayList<>();
		days.addAll(user.getDays());
		return days;
	}

	public void save(User user) {

		persist(user);
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		delete(user);
	}

}
