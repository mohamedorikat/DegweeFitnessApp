package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.User;
import com.degwee.utils.CustomHibernateDaoSupport;
@Repository
public class UserDao extends CustomHibernateDaoSupport {
	@Transactional
	public void save(User user) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(user);
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void update(User user) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.update(user);
				session.flush();
				return null;
			}
		});
	}

	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	public User findUserById(Integer id) {
		User user = null;
		List singleList = getHibernateTemplate().find("from User a where a.id=?", id);
		if (singleList != null)
			user = (User) singleList.get(0);
		return user;

	}
	
	public User findUserByUserNamePass(String userName,String Password ) {
		User user = null;
		List singleList = getHibernateTemplate().find("from User a where a.userName like ? and a.password like ?", userName,Password);
		if (singleList != null)
			user = (User) singleList.get(0);
		return user;

	}

	public List<User> findAllUser() {
		List<User> userList = (List<User>) getHibernateTemplate().find("from User");
		return userList;

	}
}
