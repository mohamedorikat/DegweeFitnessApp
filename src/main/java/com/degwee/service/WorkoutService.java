package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.daos.WorkoutDao;
import com.degwee.model.Workout;

@Service
public class WorkoutService {

	@Autowired
	public WorkoutDao workoutDao;

	@Transactional
	public void save(Workout workout) {
		workoutDao.save(workout);
	}

	public Workout findWorkoutById(Integer id) {
		if (id != null)
			return workoutDao.findWorkoutById(id);
		return null;
	}

	public List<Workout> findAllWorkouts() {
		return workoutDao.findAllWorkouts();
	}

	@Transactional
	public void update(Workout workout) {
		workoutDao.update(workout);
	}

	public void delete(Workout workout) {
		workoutDao.delete(workout);
	}

}
