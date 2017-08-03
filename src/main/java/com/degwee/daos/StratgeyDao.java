package com.degwee.daos;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Stratgey;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class StratgeyDao extends CustomHibernateDaoSupport {

	
	@Transactional
	public void save(Stratgey Stratgey) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(Stratgey);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void update(Stratgey Stratgey) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.update(Stratgey);
				session.flush();
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Stratgey Stratgey) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(Stratgey);
				session.flush();
				return null;
			}
		});
	}

	public Stratgey findStratgeyById(Integer id) {
		Stratgey Stratgey = null;
		List singleList = getHibernateTemplate().find("from Stratgey a where a.id=?", id);
		if (singleList != null)
			Stratgey = (Stratgey) singleList.get(0);
		return Stratgey;

	}

	public List<Stratgey> findAllStratgeys() {
		List<Stratgey> Stratgeys = (List<Stratgey>) getHibernateTemplate().find("from Stratgey");
		return Stratgeys;

	}

}
