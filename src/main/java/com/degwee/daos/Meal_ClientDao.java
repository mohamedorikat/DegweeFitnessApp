package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Client;
import com.degwee.model.Meal_Client;
import com.degwee.model.SubMeal;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class Meal_ClientDao extends CustomHibernateDaoSupport {

	@Transactional
	public void save(Meal_Client meal_Client) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(meal_Client);
	}

	@Transactional
	public void update(Meal_Client meal_Client) {
		getHibernateTemplate().update(meal_Client);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Meal_Client meal_Client) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(meal_Client);
				session.flush();
				return null;
			}
		});
	}

	public Meal_Client findMeal_ClientById(Integer id) {
		Meal_Client meal_Client = null;
		List singleList = getHibernateTemplate().find("from Meal_Client a where a.Id=?", id);
		if (singleList != null)
			meal_Client = (Meal_Client) singleList.get(0);
		return meal_Client;

	}

	public List<Meal_Client> findAllMeal_Client() {
		List<Meal_Client> meal_ClientList = (List<Meal_Client>) getHibernateTemplate().find("from Meal_Client");
		return meal_ClientList;

	}

	public Meal_Client findMealClientBySubMealClient(Client client, SubMeal subMeal) {
		Meal_Client meal_Client = null;
		List singleList = getHibernateTemplate().find("from Meal_Client a where a.client=? AND a.subMeal=?", client,
				subMeal);
		if (singleList != null)
			meal_Client = (Meal_Client) singleList.get(0);
		return meal_Client;

	}

}
