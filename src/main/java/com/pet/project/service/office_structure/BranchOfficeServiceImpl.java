package com.pet.project.service.office_structure;

import com.pet.project.dao.office_structure.BranchOfficeDao;
import com.pet.project.model.office_structure.BranchOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("branchOfficeService")
@Transactional
public class BranchOfficeServiceImpl implements BranchOfficeService {
    @Autowired
    BranchOfficeDao branchOfficeDao;

    @Override
    public BranchOffice findById(int id) {
        return branchOfficeDao.findById(id);
    }

    @Override
    public void save(BranchOffice branchOffice) {
        branchOfficeDao.save(branchOffice);
    }

    @Override
    public void update(BranchOffice branchOffice) {
        BranchOffice branchOffice1=findById(branchOffice.getId_branch());
        if(branchOffice1!=null){
            branchOffice1.setName_office(branchOffice.getName_office());
            branchOffice1.setAddress(branchOffice.getAddress());
            save(branchOffice1);
        }
    }

    @Override
    public List<BranchOffice> findAll() {
        return branchOfficeDao.findAll();
    }

    @Override
    public void delete(int id) {
        branchOfficeDao.deleteById(id);
    }
}
