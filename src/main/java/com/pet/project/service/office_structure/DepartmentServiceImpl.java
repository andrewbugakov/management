package com.pet.project.service.office_structure;

import com.pet.project.dao.office_structure.DepartmentDao;
import com.pet.project.model.office_structure.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartamentService {
    @Autowired
    DepartmentDao departmentDao;

    @Override
    public Department findById(int id) {
        return departmentDao.findById(id);
    }

    @Override
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Override
    public void update(Department department) {
        Department department1=departmentDao.findById(department.getId_department());
        if(department1!=null){
            department1.setTitle(department.getTitle());
            department1.setBranchOffice(department.getBranchOffice());
            department1.setEmployees(department.getEmployees());
            save(department1);
        }
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public void delete(int id) {
        departmentDao.deleteById(id);
    }
}
