package com.degwee.service;

import com.degwee.model.Stratgey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.daos.StratgeyDao;

@Service
public class StratgeyService {

	@Autowired
	public StratgeyDao stratgeyDao;

	@Transactional
	public void save(Stratgey stratgey) {
			stratgeyDao.save(stratgey);
	}

	public Stratgey findStratgeyById(Integer stratgeyid) {
		if (stratgeyid != null)
			return stratgeyDao.findStratgeyById(stratgeyid);
		return null;
	}

	public List<Stratgey> findAllStratgeys() {
		return stratgeyDao.findAllStratgeys();
	}

	@Transactional
	public void update(Stratgey stratgey) {
		stratgeyDao.update(stratgey);
	}

	public void delete(Stratgey stratgey) {
		stratgeyDao.delete(stratgey);
	}

}
