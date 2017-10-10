package com.pet.project.dao.office_structure;

import com.pet.project.dao.AbstractDao;
import com.pet.project.model.office_structure.Department;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("deptDao")
public class DepartmentDaoImpl extends AbstractDao<Integer, Department> implements DepartmentDao {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class);

    @Override
    public Department findById(int id) {
        Department department=getByKey(id);
        if(department!=null){
            Hibernate.initialize(department.getEmployees());
        }
        return department;
    }

    @Override
    public Department findByTitle(String title) {
        logger.info("Title : {}", title);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("title", title));
        Department department = (Department) crit.uniqueResult();
        if(department!=null){
            Hibernate.initialize(department.getEmployees());
        }
        return department;
    }

    @Override
    public List<Department> findAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Department> departments = (List<Department>) criteria.list();

        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        for(Department department : departments){
            Hibernate.initialize(department.getEmployees());
        }
        ArrayList<Department> departments1=new ArrayList<>();
        departments1.addAll(departments);
        return departments1;
    }

    @Override
    public void save(Department department) {
        persist(department);
    }

    @Override
    public void deleteByName(String title) {
        Department department=findByTitle(title);
        delete(department);
    }

    @Override
    public void deleteById(int id) {
        Department department=findById(id);
        delete(department);
    }
}
