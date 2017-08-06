package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Daily_Workout;
import com.degwee.model.Day;
import com.degwee.model.Set;
import com.degwee.model.Workout_Muscle;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class Daily_WorkoutDao extends CustomHibernateDaoSupport {

	@Transactional
	public void save(Daily_Workout daily_Workout) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(daily_Workout);
	}

	@Transactional
	public void update(Daily_Workout daily_Workout) {
		getHibernateTemplate().update(daily_Workout);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Daily_Workout daily_Workout) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(daily_Workout);
				session.flush();
				return null;
			}
		});
	}

	public Daily_Workout findDaily_WorkoutById(Integer id) {
		Daily_Workout daily_Workout = null;
		List singleList = getHibernateTemplate().find("from daily_Workout a where a.id=?", id);
		if (singleList != null)
			daily_Workout = (Daily_Workout) singleList.get(0);
		return daily_Workout;

	}

	public List<Daily_Workout> findAllDaily_Workout() {
		List<Daily_Workout> daily_WorkoutList = (List<Daily_Workout>) getHibernateTemplate().find("from daily_workout");
		return daily_WorkoutList;

	}

	public Daily_Workout findDaily_WorkoutByDaySetWorkout_Muscle(Day day, Set set, Workout_Muscle workout_Muscle) {
		Daily_Workout daily_Workout = null;
		List singleList = getHibernateTemplate().find(
				"from daily_Workout a where a.day=? AND a.set=? AND a.workout_Muscle=?", day, set, workout_Muscle);
		if (singleList != null)
			daily_Workout = (Daily_Workout) singleList.get(0);
		return daily_Workout;

	}

}
