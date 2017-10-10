package com.pet.project.model.office_structure;

import com.pet.project.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BRANCHOFFICE")
public class BranchOffice {
    @Id
    @GeneratedValue
    @Column(name="ID_BRANCH")
    private Integer id_branch;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="NAME_OFFICE")
    private String name_office;
    @OneToMany(mappedBy="branchOffice",cascade = CascadeType.ALL)
    private Set<Department> departments=new HashSet<>();
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User manager;

    @Override
    public String toString() {
        return "BranchOffice{" +
                "id_branch=" + id_branch +
                ", address='" + address + '\'' +
                ", name_office='" + name_office + '\'' +
                ", managerid=" + managerid +
                ", manager=" + manager +
                '}';
    }

    @Column(name = "managerid")
    private Integer managerid;
    public BranchOffice() {
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public BranchOffice(String address, String name_office) {
        this.address = address;
        this.name_office = name_office;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Integer getId_branch() {
        return id_branch;
    }

    public void setId_branch(Integer id_branch) {
        this.id_branch = id_branch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName_office() {
        return name_office;
    }

    public void setName_office(String name_office) {
        this.name_office = name_office;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}
