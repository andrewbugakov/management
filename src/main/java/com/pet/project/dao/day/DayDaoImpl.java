package com.pet.project.dao.day;

import com.pet.project.dao.AbstractDao;
import com.pet.project.model.User;
import com.pet.project.model.day.Day;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository("dayDao")
public class DayDaoImpl extends AbstractDao<Long, Day> implements DayDao {
	private static final Logger logger = LoggerFactory.getLogger(DayDaoImpl.class);

	@Override
	public Day getCurrDay(User user) {
		List<Day> days=createEntityCriteria().add(Restrictions.and(Restrictions.eq("user",user),Restrictions.eqOrIsNull("endTime",null))).list();
//		System.out.println(days1.get(0).getId_day());
//		List<Day> days=createSqlQuery("select * from DAY where id="+user.getId()+" and endtime is null;");
		if(days.size()<1){
			Day day=new Day();
			day.setDay(new Date());
			day.setEmployee(user);
			user.getDays().add(day);
			persist(day);
			System.err.println("created new day");
			return day;
		}else {
			System.err.println("founded cur day");
			Day day =(Day)days.get(0);
			if(day!=null){
				Hibernate.initialize(day.getTasks());
			}
			System.out.println(day);
			return day;
		}
	}

	public Day findById(long id) {
		Day day = getByKey(id);
		if(day!=null){
			Hibernate.initialize(day.getPause());
		}
		return day;
	}

	@Override
	public void save(Day day) {
		persist(day);
	}

	@Override
	public void deleteById(long id) {
		Day day=getByKey(id);
		delete(day);
	}

	@Override
	public List<Day> findAllDays() {
		Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Day> days = (List<Day>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		for(Day day : days){
			Hibernate.initialize(day.getPause());
		}
		ArrayList<Day> arrayList=new ArrayList<>();
		arrayList.addAll(days);
		return arrayList;
	}

}
