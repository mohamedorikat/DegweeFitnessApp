package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.degwee.daos.Client_DailyWorkoutDao;
import com.degwee.model.Client;
import com.degwee.model.Client_DailyWorkout;
import com.degwee.model.Daily_Workout;

@Service
public class Client_DailyWorkoutService {
	@Autowired
	public Client_DailyWorkoutDao client_DailyWorkoutDao;
	@Autowired
	Daily_WorkoutService daily_WorkoutService;

	public void save(Client_DailyWorkout client_DailyWorkout) {
		client_DailyWorkoutDao.save(client_DailyWorkout);
	}

	public Client_DailyWorkout findClient_DailyWorkoutById(Integer client_DailyWorkoutID) {
		return client_DailyWorkoutDao.findClient_DailyWorkoutById(client_DailyWorkoutID);
	}

	public List<Client_DailyWorkout> findAllClient_DailyWorkouts() {
		return client_DailyWorkoutDao.findAllClient_DailyWorkout();
	}

	public void update(Client_DailyWorkout client_DailyWorkout) {
		client_DailyWorkoutDao.update(client_DailyWorkout);
	}

	public void delete(Client_DailyWorkout client_DailyWorkout) {
		client_DailyWorkoutDao.delete(client_DailyWorkout);
	}

	public Client_DailyWorkout findClientDailyWorkoutByClientDailyWorkout(Client client, Daily_Workout daily_Workout) {
		return client_DailyWorkoutDao.findClientDailyWorkoutByClientDailyWorkout(client, daily_Workout);
	}
}
