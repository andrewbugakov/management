package com.pet.project.model.task;

import javax.persistence.*;

@Entity
@Table(name="TASKTYPE")
public class TaskType {
    @Id
    @GeneratedValue
    @Column(name="ID_TYPE")
    private long id_type;
    @Column(name="TYPETASK",unique = true)
    private String type_task;
    public TaskType() {
    }

    public TaskType(String type_task) {
        this.type_task = type_task;
    }

    public long getId_type() {
        return id_type;
    }

    public void setId_type(long id_type) {
        this.id_type = id_type;
    }

    public String getType_task() {
        return type_task;
    }

    public void setType_task(String type_task) {
        this.type_task = type_task;
    }
}
