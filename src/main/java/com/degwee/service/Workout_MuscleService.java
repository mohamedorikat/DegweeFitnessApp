package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.degwee.daos.Workout_MuscleDao;
import com.degwee.model.Muscle;
import com.degwee.model.Workout;
import com.degwee.model.Workout_Muscle;

@Service
public class Workout_MuscleService {

	@Autowired
	public Workout_MuscleDao workout_MuscleDao;

	public void save(Workout_Muscle workout_Muscle) {
		workout_MuscleDao.save(workout_Muscle);
	}

	public void update(Workout_Muscle workout_Muscle) {
		workout_MuscleDao.update(workout_Muscle);
	}

	public Workout_Muscle findWorkout_MuscletById(Integer id) {
		return workout_MuscleDao.findWorkout_MuscletById(id);

	}

	public List<Workout_Muscle> findAllWorkout_Muscle() {
		return workout_MuscleDao.findAllWorkout_Muscle();

	}

	public Workout_Muscle findWorkout_MuscleByWorkoutMusclet(Workout workout, Muscle muscle) {
		return workout_MuscleDao.findWorkout_MuscleByWorkoutMusclet(workout, muscle);
	}

	public List<Workout_Muscle> findWorkout_MuscleByWorkout(Workout workout) {
		return workout_MuscleDao.findWorkout_MuscleByWorkout(workout);
	}

	public boolean deleteWorkoutMuscleByWorkout(Workout workout) {
		List<Workout_Muscle> deletedList = this.findWorkout_MuscleByWorkout(workout);
		boolean existed = false;
		if (deletedList != null && !deletedList.isEmpty()) {
			existed = true;
			for (Workout_Muscle workouMuscle : deletedList)
				workout_MuscleDao.delete(workouMuscle);

		} else
			existed = false;
		return existed;

	}
	public void saveWorkoutWithMuscleList(Workout workout,List<Muscle> muscleList)
	{
		Workout_Muscle workoutMuscleObj=null;
		for(Muscle muscle:muscleList)
		{
			workoutMuscleObj=new Workout_Muscle();
			workoutMuscleObj.setMuscle(muscle);
			workoutMuscleObj.setWorkout(workout);
			this.save(workoutMuscleObj);
		}
			
	}

}
