package com.pet.project.model.day;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="PAUSE")
public class Pause {


    @Id
    @GeneratedValue
    @Column(name="ID_PAUSE")
    private long id_pause;
    @Column(name="STARTTIME")
    private Date startTime;
    @Column(name="ENDTIME")
    private Date endTime;
    @Column(name="REASON")
    private String reason;
    @ManyToOne
    @JoinColumn(name="id_day")
    private Day day;

    public Pause() {
    }

    public Pause(Date startTime, Date endTime, String reason, Day day) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.day = day;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public long getId_pause() {
        return id_pause;
    }

    public void setId_pause(long id_pause) {
        this.id_pause = id_pause;
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
        return reason;
    }

    public void setDesc(String desc) {
        this.reason = desc;
    }
    @Override
    public String toString() {
        return "Pause{" +
                "id_pause=" + id_pause +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", reason='" + reason + '\'' +
                ", day=" + day.getId_day() +
                '}';
    }
}
