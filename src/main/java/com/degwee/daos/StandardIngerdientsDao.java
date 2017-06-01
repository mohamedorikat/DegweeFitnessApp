package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.StandardIngerdients;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class StandardIngerdientsDao extends CustomHibernateDaoSupport {
	@Transactional
	public void save(StandardIngerdients standardIngerdients) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(standardIngerdients);
	}

	@Transactional
	public void update(StandardIngerdients standardIngerdients) {
		getHibernateTemplate().update(standardIngerdients);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(StandardIngerdients standardIngerdients) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(standardIngerdients);
				session.flush();
				return null;
			}
		});
	}

	public StandardIngerdients findStandardIngerdientsById(Integer id) {
		StandardIngerdients standardIngerdients = null;
		List singleList = getHibernateTemplate().find("from StandardIngerdients a where a.Id=?", id);
		if (singleList != null)
			standardIngerdients = (StandardIngerdients) singleList.get(0);
		return standardIngerdients;

	}

	public List<StandardIngerdients> findAllStandardIngerdients() {
		List<StandardIngerdients> standardIngerdientsList = (List<StandardIngerdients>) getHibernateTemplate()
				.find("from StandardIngerdients");
		return standardIngerdientsList;

	}

}
