package com.pet.project.dao;

import com.pet.project.model.day.Day;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("statDao")
public class StatDaoImpl extends AbstractDao<Integer, Day> implements StatDao{
    String selectefficient="select t.titletask,(extract ( hour  from (actualend-actualstart) ) * 60*60 + extract (  minutes from (actualend-actualstart) ) * 60 + extract  ( seconds from (actualend-actualstart) )) duration from task as t inner join day as d on d.id_day=t.id_day where d.id=4 and actualend-actualstart is not null ORDER BY duration desc ;";
    @Override
    public List getLates(int id) {
//        List list=getSession().createCriteria(Day.class).add(Restrictions.)

        //date,id,(starttime-'8:00')
        List list=getSession()
                //select date,id,cast((starttime-'8:00') as varchar) from day where starttime>'8:00'
                //select t.titletask,(extract ( hour  from (actualend-actualstart) ) * 60*60 + extract (  minutes from (actualend-actualstart) ) * 60 + extract  ( seconds from (actualend-actualstart) )) duration from task as t inner join day as d on d.id_day=t.id_day where d.id=4 and actualend-actualstart is not null ORDER BY duration desc ;
                .createSQLQuery("select distinct on(date) date,id,extract (hour  from (starttime-'8:00') ) * 60 \n" +
                        "+ extract (  minutes from (starttime-'8:00') ) late\n" +
                        " ,cast((starttime-'8:00') as varchar) from day where starttime>'8:00' and id="+id+";")
                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                .list();
        System.out.println(list.size());
        return list;
    }

    @Override
    public List getEarlies(int id) {
        List list=getSession()
                .createSQLQuery("select distinct on(date) date,id,extract (hour  from ('17:00'-endtime) ) * 60 \n" +
                        "+ extract (  minutes from ('17:00'-endtime) ) early\n" +
                        " ,cast(('17:00'-endtime) as varchar) from day where endtime<'17:00' and id="+id+";")
                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                .list();
        System.out.println(list.size());
        return list;
    }

    @Override
    public List getEfficient(int id) {
        List list=getSession()
                .createSQLQuery("select t.titletask title,(extract ( hour  from (actualend-actualstart) ) * 60*60 + extract (  minutes from (actualend-actualstart) ) * 60 + extract  ( seconds from (actualend-actualstart) )) duration from task as t inner join day as d on d.id_day=t.id_day where d.id="+id+" and actualend-actualstart is not null ORDER BY duration desc ;")
                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                .list();
        System.out.println(list.size());
        return list;
    }
}
