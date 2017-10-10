package com.pet.project.service.meeting;

import com.pet.project.dao.meeting.MeetingDao;
import com.pet.project.model.User;
import com.pet.project.model.meeting.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("meetingService")
@Transactional
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    MeetingDao meetingDao;

    @Override
    public void addToMeeting(User user, Meeting meeting) {
        meeting.getEmployees().add(user);
        user.getMeetings().add(meeting);
        meetingDao.save(meeting);
    }

    @Override
    public void save(Meeting meeting) {
        meetingDao.save(meeting);
    }

    @Override
    public void delete(long id) {
        meetingDao.deleteById(id);
    }

    @Override
    public Meeting findById(int id_meeting) {

        return meetingDao.findById(id_meeting);
    }
}
