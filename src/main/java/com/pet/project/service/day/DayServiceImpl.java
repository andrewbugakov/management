package com.pet.project.service.day;

import com.pet.project.dao.day.PauseDao;
import com.pet.project.dao.task.TaskDao;
import com.pet.project.model.User;
import com.pet.project.model.day.Day;
import com.pet.project.model.day.Pause;
import com.pet.project.model.task.Task;
import com.pet.project.utils.Utils;
import com.pet.project.dao.day.DayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("dayService")
@Transactional
public class DayServiceImpl implements DayService{
    @Autowired
    DayDao dayDao;
    @Autowired
    PauseDao pauseDao;
    @Autowired
    TaskDao taskDao;
    @Override
    public Day getCurrDay(User user) {
        return dayDao.getCurrDay(user);
    }

    @Override
    public void startDay(User user) {
        Day day=getCurrDay(user);
        day.setStartTime(Utils.getCurrTime());
        dayDao.save(day);
    }

    @Override
    public void stopDay(User user) {
        Day day=getCurrDay(user);
        day.setEndTime(Utils.getCurrTime());
        dayDao.save(day);
    }

    @Override
    public Pause getCurrPause(User user) {
        return pauseDao.getCurrPause(getCurrDay(user));
    }

    @Override
    public void startPause(User user) {
        Pause pause=getCurrPause(user);
        pause.setStartTime(new Date());
        pauseDao.save(pause);
    }

    @Override
    public void addCurPauseDesc(User user, String desc) {
        Pause pause=getCurrPause(user);
        pause.setDesc(desc);
        pauseDao.save(pause);
    }


    @Override
    public void stopPause(User user) {
        Pause pause=getCurrPause(user);
        pause.setEndTime(new Date());
        pauseDao.save(pause);
    }

    @Override
    public void save(Pause pause) {
        pauseDao.save(pause);
    }

    @Override
    public void save(Day day) {
        dayDao.save(day);
    }

    @Override
    public void update(User user, Task task) {
        Day day=getCurrDay(user);
        Task task1=taskDao.findById(task.getId_task());
        day.getTasks().add(task1);
        taskDao.save(task1);
    }

    @Override
    public void updateDay(User user, String desc, List<Integer> tasks) {
        Day day=getCurrDay(user);
        day.setDes(desc);
        Task task;
        System.err.println("task size "+tasks.size());
        for(int i=0;i<tasks.size();i++){
            task=taskDao.findById(tasks.get(i));
            task.setDay(day);
            day.getTasks().add(task);
            task.setUserWhoDo(null);
            taskDao.save(task);
        }

    }
}
