package com.pet.project.model.task;


import com.pet.project.model.User;
import com.pet.project.model.day.Day;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="TASK")
public class Task {
    public enum PriorityEn{ВЫСОКИЙ,СРЕДНИЙ,НИЗКИЙ};
    @Id
    @GeneratedValue
    @Column(name="ID_ORDER_BY_DAY")
    private long id_task;
    @Column(name="TIMESTAMPCREATE")
    private Date timeStampCreate;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name="DEADLINE")
    private Date deadline;
    @Column(name="ACTUALSTART")
    private Time actualStart;
    @Column(name="ACTUALEND")
    private Time actualEnd;
    @Column(name="TITLETASK")
    private String titleTask;
    @Column(name="description")
    private String desc;
    @Column(name="PRIORITY")
    private PriorityEn priority;
    @ManyToOne
    @JoinColumn(name="id_type")
    private TaskType taskType;
    @ManyToOne
    @JoinColumn(name="id_user_who_create")
    private User userWhoCreate;
    @ManyToOne
    @JoinColumn(name="id_user_who_do")
    private User userWhoDo;
    @ManyToOne
    @JoinColumn(name="id_day")
    private Day day;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "us_col",
            joinColumns = { @JoinColumn(name = "task_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> collaborators=new HashSet<>();

//    @ManyToOne
//    @JoinColumn(name="id_order_by_day")
//    private OrderByDay orderByDay;

    public Task() {
    }

    public Set<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(Set<User> collaborators) {
        this.collaborators = collaborators;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public PriorityEn getPriority() {
        return priority;
    }

    public void setPriority(PriorityEn priority) {
        this.priority = priority;
    }
//
//    public OrderByDay getOrderByDay() {
//        return orderByDay;
//    }
//
//    public void setOrderByDay(OrderByDay orderByDay) {
//        this.orderByDay = orderByDay;
//    }

    public long getId_task() {
        return id_task;
    }

    public void setId_task(long id_task) {
        this.id_task = id_task;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public User getUserWhoCreate() {        return userWhoCreate;
    }

    public void setUserWhoCreate(User userWhoCreate) {
        this.userWhoCreate = userWhoCreate;
    }

    public User getUserWhoDo() {
        return userWhoDo;
    }

    public void setUserWhoDo(User userWhoDo) {
        this.userWhoDo = userWhoDo;
    }

    public Date getTimeStampCreate() {
        return timeStampCreate;
    }

    public void setTimeStampCreate(Date timeStampCreate) {
        this.timeStampCreate = timeStampCreate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Time getActualStart() {
        return actualStart;
    }

    public void setActualStart(Time actualStart) {
        this.actualStart = actualStart;
    }

    public Time getActualEnd() {
        return actualEnd;
    }

    public void setActualEnd(Time actualEnd) {
        this.actualEnd = actualEnd;
    }

    public String getTitleTask() {
        return titleTask;
    }

    public void setTitleTask(String titleTask) {
        this.titleTask = titleTask;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
