package com.pet.project.service.task;

import com.pet.project.model.task.Task;
import com.pet.project.dao.day.DayDao;
import com.pet.project.dao.task.TaskDaoImpl;
import com.pet.project.model.day.Day;
import com.pet.project.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDaoImpl taskDao;
    @Autowired
    DayDao dayDao;
    @Override
    public Task findById(long id) {
        return taskDao.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Override
    public void save(Task task) {
        taskDao.save(task);
    }

    @Override
    public void deleteById(long id) {
        taskDao.deleteById(id);
    }
    @Override
    public String startstopday(long id){
        Task task=taskDao.findById(id);
        if (task.getActualStart()==null){
            task.setActualStart(Utils.getCurrTime());
            save(task);
            return "started";
        }else{
            if(task.getActualEnd()==null){
                task.setActualEnd(Utils.getCurrTime());
                Day day=dayDao.getCurrDay(task.getUserWhoDo());
                day.getTasks().add(task);
                task.setDay(day);
//                task.setUserWhoDo(null);
                save(task);

                return "stopped";
            }else{
                return "already stopped";
            }
        }
    }

    @Override
    public List<Task> findByID(int id) {
        return taskDao.findAllById(id);
    }

    @Override
    public List<Task> findByIdDay(long id) {
        return taskDao.findAllByIdDay(id);
    }

    @Override
    public List<Task> findOutOfDeadlineTasks(long id) {
        return null;
    }

    @Override
    public List<Task> findClosedTasks(long id) {
        return null;
    }


}
