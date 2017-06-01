package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.degwee.daos.FolderPathDao;
import com.degwee.model.FolderPath;

@Service
public class FolderPathService {

	@Autowired
	public FolderPathDao folderPathDao;

	public void save(FolderPath folderPath) {
		folderPathDao.save(folderPath);
	}

	public FolderPath findFolderPathById(Integer id) {
		return folderPathDao.findFolderPathById(id);
		
	}

	public List<FolderPath> findAllFolderPaths() {
		return folderPathDao.findAllFolderPath();
	}

	public void update(FolderPath folderPath) {
		folderPathDao.update(folderPath);
	}

	public void delete(FolderPath folderPath) {
		folderPathDao.delete(folderPath);
	}

}
