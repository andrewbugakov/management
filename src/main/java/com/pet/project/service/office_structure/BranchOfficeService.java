package com.pet.project.service.office_structure;

import com.pet.project.model.office_structure.BranchOffice;

import java.util.List;

public interface BranchOfficeService {
    BranchOffice findById(int id);
    void save(BranchOffice branchOffice);
    void update(BranchOffice branchOffice);
    List<BranchOffice> findAll();
    void delete(int id);

}
