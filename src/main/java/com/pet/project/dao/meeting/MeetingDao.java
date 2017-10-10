package com.pet.project.dao.meeting;


import com.pet.project.model.meeting.Meeting;

import java.util.List;

public interface MeetingDao {
    Meeting findById(long id);
    List<Meeting> findAll();
    void save(Meeting meeting);
    void deleteById(long id);
}
