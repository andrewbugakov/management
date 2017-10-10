package com.pet.project.dao.meeting;

import com.pet.project.dao.AbstractDao;
import com.pet.project.model.meeting.Meeting;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("meetingDao")
public class MeetingDaoImpl extends AbstractDao<Long, Meeting> implements MeetingDao {
    private static final Logger logger = LoggerFactory.getLogger(MeetingDaoImpl.class);

    @Override
    public Meeting findById(long id) {
        Meeting meeting=getByKey(id);
        if(meeting!=null){
            Hibernate.initialize(meeting.getEmployees());
        }
        return meeting;
    }


    @Override
    public List<Meeting> findAll() {
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("desc"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Meeting> meetings = (List<Meeting>) criteria.list();
        for(Meeting meeting : meetings){
            Hibernate.initialize(meeting.getEmployees());
        }
        ArrayList<Meeting> meetings1=new ArrayList<>();
        meetings1.addAll(meetings);
        return meetings1;
    }

    @Override
    public void save(Meeting meeting) {
        persist(meeting);
    }

    @Override
    public void deleteById(long id) {
        Meeting meeting=findById(id);
        delete(meeting);
    }
}
