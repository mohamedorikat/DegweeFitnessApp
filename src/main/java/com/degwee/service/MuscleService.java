package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.daos.MuscleDao;
import com.degwee.model.Muscle;

@Service
public class MuscleService {

	@Autowired
	public MuscleDao muscleDao;
	
	@Transactional
	public boolean save(Muscle muscle) {
		Muscle existedMuscle = null;
		existedMuscle = muscleDao.findMuscleById(muscle.getId());
		if (existedMuscle != null) {
			return true;
		} else {
			muscleDao.save(muscle);
		}
		return false;
	}

	public Muscle findMuscleById(Integer id) {
		if (id != null)
			return muscleDao.findMuscleById(id);
		return null;
	}

	public List<Muscle> findAllMuscles() {
		return muscleDao.findAllMuscles();
	}

	@Transactional
	public void update(Muscle muscle) {
		muscleDao.update(muscle);
	}

	public void delete(Muscle muscle) {
		muscleDao.delete(muscle);
	}

}
