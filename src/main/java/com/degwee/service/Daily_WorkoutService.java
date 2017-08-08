package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.degwee.daos.Daily_WorkoutDao;
import com.degwee.model.Daily_Workout;
import com.degwee.model.Day;
import com.degwee.model.Set;
import com.degwee.model.Workout;

@Service
public class Daily_WorkoutService {
	
	@Autowired
	private Daily_WorkoutDao daily_WorkoutDao;
	
	public void save(Daily_Workout daily_Workout) {
		daily_WorkoutDao.save(daily_Workout);
	}

	public void update(Daily_Workout daily_Workout) {
		daily_WorkoutDao.update(daily_Workout);
	}
	
	public void delete(Daily_Workout daily_Workout) {
		 daily_WorkoutDao.delete(daily_Workout);
	}

	public Daily_Workout findDaily_WorkoutById(Integer id) {
		return daily_WorkoutDao.findDaily_WorkoutById(id);

	}

	public List<Daily_Workout> findAllDaily_Workout() {
		return daily_WorkoutDao.findAllDaily_Workout();

	}

	public Daily_Workout findDaily_WorkoutByDaySetWorkout_Muscle(Day day, Set set, Workout workout) {
	return daily_WorkoutDao.findDaily_WorkoutByDaySetWorkout_Muscle(day, set, workout);

	}

}
