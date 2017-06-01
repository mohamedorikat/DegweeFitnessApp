package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.daos.StandardIngerdientsDao;
import com.degwee.model.FolderPath;
import com.degwee.model.StandardIngerdients;

@Service
public class StandardIngerdientsService {
	@Autowired
	public StandardIngerdientsDao standardIngerdientsDao;
	@Autowired
	public FolderPathService folderPathService ;
	@Transactional
	public void save(StandardIngerdients standardIngerdients) {
		standardIngerdientsDao.save(standardIngerdients);
	}

	public StandardIngerdients findStandardIngerdientsById(StandardIngerdients standardIngerdients) {
		if (standardIngerdients.getId() != null)
			return standardIngerdientsDao.findStandardIngerdientsById(standardIngerdients.getId());
		return null;
	}
	public List<StandardIngerdients> findAllStandardIngerdientss()
	{
		return standardIngerdientsDao.findAllStandardIngerdients();
	}
	public void update(StandardIngerdients standardIngerdients )
	{
		 standardIngerdientsDao.update(standardIngerdients);
	}
	@Transactional
	public void delete(StandardIngerdients standardIngerdients )
	{
		 standardIngerdientsDao.delete(standardIngerdients);
		
	}
}
