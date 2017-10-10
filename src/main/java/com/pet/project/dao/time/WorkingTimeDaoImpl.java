package com.pet.project.dao.time;

import com.pet.project.dao.AbstractDao;
import com.pet.project.model.time.WorkingTime;
import org.springframework.stereotype.Repository;

@Repository("workingTimeDao")
public class WorkingTimeDaoImpl extends AbstractDao<Integer, WorkingTime> implements WorkingTimeDao {

    @Override
    public WorkingTime getWorkingTime() {
        WorkingTime workingTime= (WorkingTime) createEntityCriteria().uniqueResult();
        if(workingTime==null){
            workingTime=new WorkingTime();
            persist(workingTime);
        }
        return workingTime;
    }

    @Override
    public void save(WorkingTime workingTime) {
        WorkingTime workingTime1;
        workingTime1=getByKey(workingTime.getId());
        workingTime1.setColvo_dnei(workingTime.getColvo_dnei());
        workingTime1.setPereriv(workingTime.getPereriv());
        workingTime1.setZavershenie(workingTime.getZavershenie());
        workingTime1.setOpazdanie(workingTime.getOpazdanie());
        workingTime1.setEdVremya(workingTime.getStartPnd()+"-"+workingTime.getEndPnd());
        update(workingTime1);
//        persist(workingTime);
    }
}
