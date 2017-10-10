package com.pet.project.service.meeting;


import com.pet.project.model.User;
import com.pet.project.model.meeting.Meeting;

public interface MeetingService {
    void addToMeeting(User user, Meeting meeting);
    void save(Meeting meeting);
    void delete(long id);

    Meeting findById(int id_meeting);
}
