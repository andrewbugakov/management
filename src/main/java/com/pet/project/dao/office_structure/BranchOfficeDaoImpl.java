package com.pet.project.dao.office_structure;

import com.pet.project.dao.AbstractDao;
import com.pet.project.model.office_structure.BranchOffice;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("branchDao")
public class BranchOfficeDaoImpl extends AbstractDao<Integer, BranchOffice> implements BranchOfficeDao {
    private static final Logger logger = LoggerFactory.getLogger(BranchOfficeDaoImpl.class);

    @Override
    public BranchOffice findById(int id) {
        BranchOffice branchOffice=getByKey(id);
        if(branchOffice!=null){
            Hibernate.initialize(branchOffice.getDepartments());
        }
        return branchOffice;
    }

    @Override
    public BranchOffice findByNameOffice(String title) {
        logger.info("Title : {}", title);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name_of_office", title));
        BranchOffice branchOffice = (BranchOffice)crit.uniqueResult();
        if(branchOffice!=null){
            Hibernate.initialize(branchOffice.getDepartments());
        }
        return branchOffice;
    }

    @Override
    public List<BranchOffice> findAll() {
        Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("title"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<BranchOffice> branchOffices = (List<BranchOffice>) criteria.list();

        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        for(BranchOffice office : branchOffices){
            Hibernate.initialize(office.getDepartments());
        }

        return branchOffices;
    }

    @Override
    public void save(BranchOffice branchOffice) {
        persist(branchOffice);
    }

    @Override
    public void deleteByOfficeName(String title) {
        BranchOffice branchOffice = findByNameOffice(title);
        delete(branchOffice);
    }

    @Override
    public void deleteById(int id) {
        BranchOffice branchOffice = findById(id);
        delete(branchOffice);
    }
}
