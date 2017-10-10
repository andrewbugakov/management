package com.pet.project.dao.office_structure;


import com.pet.project.model.office_structure.BranchOffice;

import java.util.List;

public interface BranchOfficeDao {
    BranchOffice findById(int id);
    BranchOffice findByNameOffice(String nameOffice);
    List<BranchOffice> findAll();
    void save(BranchOffice branchOffice);
    void deleteByOfficeName(String nameOffice);
    void deleteById(int id);
}
