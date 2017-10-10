package com.pet.project.model.meeting;


import com.pet.project.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="MEETING")
public class Meeting {
    @Id
    @GeneratedValue
    @Column(name="ID_MEETING")
    private long id_meeting;
    @Column(name="STARTTIME")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date startTime;
    @Column(name="ENDTIME")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date endTime;
    @Column(name="description")
    private String desc;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "meetuser",
            joinColumns = { @JoinColumn(name = "meeting_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> employees=new HashSet<>();

    public Meeting() {
    }

    public Meeting(Date startTime, Date endTime, String desc) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.desc = desc;
    }

    public long getId_meeting() {
        return id_meeting;
    }

    public void setId_meeting(long id_meeting) {
        this.id_meeting = id_meeting;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<User> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<User> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id_meeting=" + id_meeting +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", desc='" + desc + '\'' +
                ", employees=" + employees +
                '}';
    }
}
