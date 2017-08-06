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

}
