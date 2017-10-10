package com.pet.project.dao.task;

import com.pet.project.dao.AbstractDao;
import com.pet.project.model.task.Task;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskDao")
public class TaskDaoImpl extends AbstractDao<Long, Task> implements TaskDao {
    private static final Logger logger = LoggerFactory.getLogger(TaskDaoImpl.class);

    @Override
    public Task findById(long id) {
        Task task=getByKey(id);
        return task;
    }

    @Override
    public List<Task> findAll() {
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("title"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Task>  tasks = (List<Task>) criteria.list();
        return tasks;
    }

    @Override
    public List<Task> findAllById(int id_emp) {
        return createSqlQuery("select * from task where id_user_who_do="+id_emp);

    }

    @Override
    public List<Task> findAllByIdDay(long id) {
        return createSqlQuery("select * from task where id_day="+id);
    }

    @Override
    public void save(Task task) {
        persist(task);
    }

    @Override
    public void deleteById(long id) {
        Task task=findById(id);
        delete(task);
    }
}
