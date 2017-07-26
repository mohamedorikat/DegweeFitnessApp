package com.degwee.daos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.FolderPath;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class FolderPathDao extends CustomHibernateDaoSupport {
	@Transactional
	public void save(FolderPath folderPath) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(folderPath);
	}

	@Transactional
	public void update(FolderPath folderPath) {
		getHibernateTemplate().update(folderPath);
	}

	public void delete(FolderPath folderPath) {
		getHibernateTemplate().delete(folderPath);
	}

	public FolderPath findFolderPathById(Integer id) {
		FolderPath folderPath = null;
		List singleList = getHibernateTemplate().find("from FolderPath a where a.id=?", id);
		if (singleList != null || singleList.isEmpty())
			folderPath = (FolderPath) singleList.get(0);
		return folderPath;

	}
	public List<FolderPath> findAllFolderPath() {
		List<FolderPath>  folderPathList=(List<FolderPath>) getHibernateTemplate().find("from FolderPath");
		return folderPathList;

	}

}
