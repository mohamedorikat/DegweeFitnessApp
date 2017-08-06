package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.degwee.model.Muscle;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class MuscleDao extends CustomHibernateDaoSupport {

	
	@Transactional
	public void save(Muscle muscle) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(muscle);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void update(Muscle muscle) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.update(muscle);
				session.flush();
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Muscle muscle) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(muscle);
				session.flush();
				return null;
			}
		});
	}

	public Muscle findMuscleById(Integer id) {
		Muscle muscle = null;
		List singleList = getHibernateTemplate().find("from Muscle a where a.id=?", id);
		if (singleList != null)
			muscle = (Muscle) singleList.get(0);
		return muscle;

	}

	public List<Muscle> findAllMuscles() {
		List<Muscle> muscles = (List<Muscle>) getHibernateTemplate().find("from Muscle");
		return muscles;

	}


}
