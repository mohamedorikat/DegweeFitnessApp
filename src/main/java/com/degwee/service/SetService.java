package com.degwee.service;

import com.degwee.model.Set;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.daos.SetDao;

@Service
public class SetService {

	@Autowired
	public SetDao setDao;

	@Transactional
	public void save(Set set) {
			setDao.save(set);
	}

	public Set findSetById(Integer setid) {
		if (setid != null)
			return setDao.findSetById(setid);
		return null;
	}

	public List<Set> findAllSets() {
		return setDao.findAllSets();
	}

	@Transactional
	public void update(Set set) {
		setDao.update(set);
	}

	public void delete(Set set) {
		setDao.delete(set);
	}

}
