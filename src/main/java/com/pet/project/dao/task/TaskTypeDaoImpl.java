package com.pet.project.dao.task;

import com.pet.project.dao.AbstractDao;
import com.pet.project.model.task.TaskType;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("taskTypeDao")
public class TaskTypeDaoImpl extends AbstractDao<Long, TaskType> implements TaskTypeDao{

	public TaskType findById(long id) {
		return getByKey(id);
	}

	public TaskType findByType(String type) {
		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("type", type));
		return (TaskType) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskType> findAll(){
		Criteria crit = createEntityCriteria();
//		crit.addOrder(Order.asc("type"));
		return (List<TaskType>)crit.list();
	}
	
}
