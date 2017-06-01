package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Client;
import com.degwee.model.Meal;
import com.degwee.model.SubMeal;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class SubMealDao extends CustomHibernateDaoSupport{
	@Transactional
	public void save(SubMeal subMeal) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(subMeal);
	}

	@Transactional
	public void update(SubMeal subMeal) {
		getHibernateTemplate().update(subMeal);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(SubMeal subMeal) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(subMeal);
				session.flush();
				return null;
			}
		});
	}

	public SubMeal findSubMealById(Integer id) {
		SubMeal subMeal = null;
		List singleList = getHibernateTemplate().find("from SubMeal a where a.Id=?", id);
		if (singleList != null)
			subMeal = (SubMeal) singleList.get(0);
		return subMeal;

	}

	public List<SubMeal> findAllSubMeal() {
		List<SubMeal> subMealList = (List<SubMeal>) getHibernateTemplate().find("from SubMeal");
		return subMealList;

	}
	public List<SubMeal> getSubMeals(Client client,Meal meal)
	{
		List<SubMeal> singleList = (List<SubMeal>)getHibernateTemplate().find("select m.subMeal from Meal_Client m join m.subMeal s where s.meal=? and m.client=?",meal, client);
		return singleList;
	}
}
