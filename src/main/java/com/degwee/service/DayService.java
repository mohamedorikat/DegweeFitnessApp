package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.daos.DayDao;
import com.degwee.model.Day;

@Service
public class DayService {

	@Autowired
	public DayDao dayDao;

	@Transactional
	public boolean save(Day day) {
		Day existedDay = null;
		existedDay = dayDao.findDayById(day.getId());
		if (existedDay != null) {
			return true;
		} else {
			dayDao.save(day);
		}
		return false;
	}

	public Day findDayById(Integer dayId) {
		if (dayId != null)
			return dayDao.findDayById(dayId);
		return null;
	}

	public List<Day> findAllDays() {
		return dayDao.findAllDays();
	}

	@Transactional
	public void update(Day day) {
		dayDao.update(day);
	}

	public void delete(Day day) {
		dayDao.delete(day);
	}

}
