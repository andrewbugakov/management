package com.pet.project.service.office_structure;

import com.pet.project.model.office_structure.Department;

import java.util.List;

public interface DepartamentService {
    Department findById(int id);
    void save(Department department);
    void update(Department department);
    List<Department> findAll();
    void delete(int id);
}
