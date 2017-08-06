package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Muscle;
import com.degwee.model.Stratgey;
import com.degwee.model.Workout;
import com.degwee.model.Workout_Muscle;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class Workout_MuscleDao extends CustomHibernateDaoSupport {

	@Transactional
	public void save(Workout_Muscle workout_Muscle) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(workout_Muscle);
	}

	@Transactional
	public void update(Workout_Muscle workout_Muscle) {
		getHibernateTemplate().update(workout_Muscle);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Workout_Muscle workout_Muscle) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(workout_Muscle);
				session.flush();
				return null;
			}
		});
	}

	public Workout_Muscle findWorkout_MuscletById(Integer id) {
		Workout_Muscle workout_Muscle = null;
		List singleList = getHibernateTemplate().find("from workout_muscle a where a.Id=?", id);
		if (singleList != null)
			workout_Muscle = (Workout_Muscle) singleList.get(0);
		return workout_Muscle;

	}

	public List<Workout_Muscle> findAllWorkout_Muscle() {
		List<Workout_Muscle> workoutMuscleList = (List<Workout_Muscle>) getHibernateTemplate()
				.find("from Workout_Muscle");
		return workoutMuscleList;

	}

	public Workout_Muscle findWorkout_MuscleByWorkoutMusclet(Workout workout, Muscle muscle) {
		Workout_Muscle workout_Muscle = null;
		List singleList = getHibernateTemplate().find("from Workout_Muscle a where a.workout=? AND a.muscle=?", workout,
				muscle);
		if (singleList != null)
			workout_Muscle = (Workout_Muscle) singleList.get(0);
		return workout_Muscle;

	}
	
	public List<Workout_Muscle> findWorkout_MuscleByWorkout(Workout workout) {
		List list = getHibernateTemplate().find("from Workout_Muscle a where a.workout=?", workout);
		return list;

	}

}
