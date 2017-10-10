package com.pet.project.model.day;


import com.pet.project.model.User;
import com.pet.project.model.task.Task;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="DAY")
@SuppressWarnings("unused")
public class Day {
    @Id
    @GeneratedValue
    @Column(name="ID_DAY")
    private long id_day;
    @Column(name="DATE")
    @Temporal(TemporalType.DATE)
    private Date day;
    @Column(name="STARTTIME")
    private Time startTime;
    @Column(name="ENDTIME")
    private Time endTime;
    @Column(name="lates")
    private String latedesc;
    @Column(name="earlydesc")
    private String earlydesc;
    @Column(name="des")
    private String des;
    @ManyToOne
    @JoinColumn(name="id")
    private User user;
    @OneToMany(mappedBy="day",cascade = CascadeType.ALL)
    private Set<Pause> pause=new HashSet<>();
    @OneToMany(mappedBy="day",cascade = CascadeType.ALL)
    private Set<Task> tasks=new HashSet<>();

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "day", cascade = CascadeType.ALL)
//    private OrderByDay orderByDay=new OrderByDay(this);
    public Day() {
    }

    public Day(Date day, Timestamp startTime, Timestamp endTime, String desc, User user) {
        this.day = day;
        this.user = user;
    }
//

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLatedesc() {
        return latedesc;
    }

    public void setLatedesc(String latedesc) {
        this.latedesc = latedesc;
    }

    public String getEarlydesc() {
        return earlydesc;
    }

    public void setEarlydesc(String earlydesc) {
        this.earlydesc = earlydesc;
    }

    //    public OrderByDay getOrderByDay() {
//        return orderByDay;
//    }
//
//    public void setOrderByDay(OrderByDay orderByDay) {
//        this.orderByDay = orderByDay;
//    }
    public long getId_day() {
        return id_day;
    }

    public void setId_day(long id_day) {
        this.id_day = id_day;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Date getStartTime() {
        return startTime;
    }


    public Date getEndTime() {
        return endTime;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Pause> getPause() {
        return pause;
    }

    public void setPause(Set<Pause> pause) {
        this.pause = pause;
    }

    public User getEmployee() {
        return user;
    }

    public void setEmployee(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id_day=" + id_day +
                ", day=" + day.toGMTString() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", latedesc='" + latedesc + '\'' +
                ", earlydesc='" + earlydesc + '\'' +
                ", user=" + user +
                ", pause=" + pause +
                ", tasks=" + tasks +
                '}';
    }
}
