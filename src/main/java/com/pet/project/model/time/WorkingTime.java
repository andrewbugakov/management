package com.pet.project.model.time;

import javax.persistence.*;
import java.sql.Time;
@Entity
@Table(name="workingtime")
public class WorkingTime {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="opazdanie")
    private int opazdanie=15;
    @Column(name="zavershenie")
    private int zavershenie=15;
    @Column(name="pereriv")
    private int pereriv=60;
    @Column(name="colvo_dnei")
    private int colvo_dnei=5;
    @Column(name="startPnd")
    private String startPnd="8:00";
    @Column(name="endPnd")
    private String endPnd="17:00";
    @Column(name="startVt")
    private String startVt="8:00";
    @Column(name="endVt")
    private String endVt="17:00";
    @Column(name="startSr")
    private String startSr="8:00";
    @Column(name="endSr")
    private String endSr="17:00";
    @Column(name="startCht")
    private String startCht="8:00";
    @Column(name="endCht")
    private String endCht="17:00";
    @Column(name="startPt")
    private String startPt="8:00";
    @Column(name="endPt")
    private String endPt="17:00";
    @Column(name="startSub")
    private String startSub;
    @Column(name="endSub")
    private String endSub;
    @Column(name="startVs")
    private String startVs;
    @Column(name="endVs")
    private String endVs;

    public WorkingTime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOpazdanie() {
        return opazdanie;
    }

    public void setOpazdanie(int opazdanie) {
        this.opazdanie = opazdanie;
    }

    public int getZavershenie() {
        return zavershenie;
    }

    public void setZavershenie(int zavershenie) {
        this.zavershenie = zavershenie;
    }

    public int getPereriv() {
        return pereriv;
    }

    public void setPereriv(int pereriv) {
        this.pereriv = pereriv;
    }

    public int getColvo_dnei() {
        return colvo_dnei;
    }

    public void setColvo_dnei(int colvo_dnei) {
        this.colvo_dnei = colvo_dnei;
    }

    public String getStartPnd() {
        return startPnd;
    }

    public void setStartPnd(String startPnd) {
        this.startPnd = startPnd;
    }

    public String getEndPnd() {
        return endPnd;
    }

    public void setEndPnd(String endPnd) {
        this.endPnd = endPnd;
    }

    public String getStartVt() {
        return startVt;
    }

    public void setStartVt(String startVt) {
        this.startVt = startVt;
    }

    public String getEndVt() {
        return endVt;
    }

    public void setEndVt(String endVt) {
        this.endVt = endVt;
    }

    public String getStartSr() {
        return startSr;
    }

    public void setStartSr(String startSr) {
        this.startSr = startSr;
    }

    public String getEndSr() {
        return endSr;
    }

    public void setEndSr(String endSr) {
        this.endSr = endSr;
    }

    public String getStartCht() {
        return startCht;
    }

    public void setStartCht(String startCht) {
        this.startCht = startCht;
    }

    public String getEndCht() {
        return endCht;
    }

    public void setEndCht(String endCht) {
        this.endCht = endCht;
    }

    public String getStartPt() {
        return startPt;
    }

    public void setStartPt(String startPt) {
        this.startPt = startPt;
    }

    public String getEndPt() {
        return endPt;
    }

    public void setEndPt(String endPt) {
        this.endPt = endPt;
    }

    public String getStartSub() {
        return startSub;
    }

    public void setStartSub(String startSub) {
        this.startSub = startSub;
    }

    public String getEndSub() {
        return endSub;
    }

    public void setEndSub(String endSub) {
        this.endSub = endSub;
    }

    public String getStartVs() {
        return startVs;
    }

    public void setStartVs(String startVs) {
        this.startVs = startVs;
    }

    public String getEndVs() {
        return endVs;
    }

    public void setEndVs(String endVs) {
        this.endVs = endVs;
    }


    public void setEdVremya(String edVremya) {
        String[] vremya=edVremya.split("-");
        this.startPnd=vremya[0];
        this.startVt=vremya[0];
        this.startSr=vremya[0];
        this.startCht=vremya[0];
        this.startPt=vremya[0];
        this.endPnd=vremya[1];
        this.endVt=vremya[1];
        this.endSr=vremya[1];
        this.endCht=vremya[1];
        this.endPt=vremya[1];
    }

}
