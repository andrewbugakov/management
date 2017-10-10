package com.pet.project.model.office_structure;


import com.pet.project.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="DEPARTMENT")
public class Department {
    @Id
    @GeneratedValue
    @Column(name="ID_DEPARTMENT")
    private Integer id_department;
    @Column(name="TITLE")
    private String title;
    @OneToMany(mappedBy="department",cascade = CascadeType.ALL)
    private Set<User> employees=new HashSet<>();
    @ManyToOne
    @JoinColumn(name="id_branch")
    private BranchOffice branchOffice;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User manager;
    @Column(name = "managerid")
    private Integer managerid;
    public Department() {
    }

    public Department(String title, BranchOffice branchOffice) {
        this.title = title;
        this.branchOffice = branchOffice;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Integer getId_department() {
        return id_department;
    }

    public void setId_department(Integer id_department) {
        this.id_department = id_department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BranchOffice getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(BranchOffice branchOffice) {
        this.branchOffice = branchOffice;
    }

    public Set<User> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<User> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id_department=" + id_department +
                ", title='" + title + '\'' +
                ", manager=" + manager +
                ", managerid=" + managerid +
                '}';
    }
//    public Employee getManagerOfDepartment() {
//        return managerOfDepartment;
//    }
//
//    public void setManagerOfDepartment(Employee managerOfDepartment) {
//        this.managerOfDepartment = managerOfDepartment;
//    }
}
