package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Day;
import com.degwee.utils.CustomHibernateDaoSupport;
@Repository
public class DayDao extends CustomHibernateDaoSupport {

	
	@Transactional
	public void save(Day day) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(day);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void update(Day day) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.update(day);
				session.flush();
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Day day) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(day);
				session.flush();
				return null;
			}
		});
	}

	public Day findDayById(Integer id) {
		Day day = null;
		List singleList = getHibernateTemplate().find("from day a where a.id=?", id);
		if (singleList != null)
			day = (Day) singleList.get(0);
		return day;

	}

	public List<Day> findAllDays() {
		List<Day> days = (List<Day>) getHibernateTemplate().find("from day");
		return days;

	}


}
