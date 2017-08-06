package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Workout;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class WorkoutDao extends CustomHibernateDaoSupport {

	
	@Transactional
	public void save(Workout workout) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(workout);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void update(Workout workout) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.update(workout);
				session.flush();
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Workout workout) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(workout);
				session.flush();
				return null;
			}
		});
	}

	public Workout findWorkoutById(Integer id) {
		Workout workout = null;
		List singleList = getHibernateTemplate().find("from Workout a where a.id=?", id);
		if (singleList != null)
			workout = (Workout) singleList.get(0);
		return workout;

	}

	public List<Workout> findAllWorkouts() {
		List<Workout> workouts = (List<Workout>) getHibernateTemplate().find("from Workout");
		return workouts;

	}

}

