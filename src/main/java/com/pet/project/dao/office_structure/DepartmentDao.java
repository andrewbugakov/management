package com.pet.project.dao.office_structure;

import com.pet.project.model.office_structure.Department;

import java.util.List;

public interface DepartmentDao {
    Department findById(int id);
    Department findByTitle(String title);
    List<Department> findAll();
    void save(Department department);
    void deleteByName(String title);
    void deleteById(int id);
}
