package com.degwee.daos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Meal;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class MealDao extends CustomHibernateDaoSupport{
	
	@Transactional
	public void save(Meal meal) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(meal);
	}

	@Transactional
	public void update(Meal meal) {
		getHibernateTemplate().update(meal);
	}

	public void delete(Meal meal) {
		getHibernateTemplate().delete(meal);
	}

	public Meal findMealById(Integer id) {
		Meal meal = null;
		List singleList = getHibernateTemplate().find("from Meal a where a.Id=?", id);
		if (singleList != null)
			meal = (Meal) singleList.get(0);
		return meal;

	}

	public List<Meal> findAllMeal() {
		List<Meal> mealList = (List<Meal>) getHibernateTemplate().find("from Meal");
		return mealList;

	}
}
