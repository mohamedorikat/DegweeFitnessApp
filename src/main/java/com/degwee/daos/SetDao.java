package com.degwee.daos;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Set;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class SetDao extends CustomHibernateDaoSupport {

	
	@Transactional
	public void save(Set Set) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(Set);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void update(Set Set) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.update(Set);
				session.flush();
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Set Set) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(Set);
				session.flush();
				return null;
			}
		});
	}

	public Set findSetById(Integer id) {
		Set Set = null;
		List singleList = getHibernateTemplate().find("from Set a where a.id=?", id);
		if (singleList != null)
			Set = (Set) singleList.get(0);
		return Set;

	}

	public List<Set> findAllSets() {
		List<Set> Sets = (List<Set>) getHibernateTemplate().find("from Set");
		return Sets;

	}

}
