package com.pet.project.dao.day;

import com.pet.project.dao.AbstractDao;
import com.pet.project.model.day.Day;
import com.pet.project.model.day.Pause;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("pauseDao")
public class PauseDaoImpl extends AbstractDao<Long, Pause> implements PauseDao {
	private static final Logger logger = LoggerFactory.getLogger(PauseDaoImpl.class);
	@Override
	public Pause getCurrPause(Day day) {
		List<Pause> pauses=createEntityCriteria().add(Restrictions.and(Restrictions.eq("day",day),Restrictions.eqOrIsNull("endTime",null))).list();
//		List<Pause> pauses=createSqlQuery("select * from PAUSE where ID_DAY="+day.getId_day()+" and ENDTIME is null;");
		if(pauses.size()<1){
			Pause pause=new Pause();
			pause.setDay(day);
			day.getPause().add(pause);
			persist(pause);
			return pause;
		}else{
			return pauses.get(0);
		}
	}

	public Pause findById(long id) {
		Pause pause = getByKey(id);
		return pause;
	}

	@Override
	public void save(Pause pause) {
		Pause pause1=findById(pause.getId_pause());
		pause1.setDesc(pause.getDesc());
		pause1.setStartTime(pause.getStartTime());
		pause1.setEndTime(pause.getEndTime());
		persist(pause);
	}

	@Override
	public void deleteById(long id) {
		Pause pause=getByKey(id);
		delete(pause);
	}

	@Override
	public List<Pause> findAllPause() {
		Criteria criteria = createEntityCriteria();//.addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Pause> pauses = (List<Pause>) criteria.list();
		return pauses;
	}

}
