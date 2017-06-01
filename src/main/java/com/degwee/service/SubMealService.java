package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.daos.SubMealDao;
import com.degwee.model.Client;
import com.degwee.model.Meal;
import com.degwee.model.Meal_Client;
import com.degwee.model.SubMeal;
import com.degwee.utils.Constants;

@Service
public class SubMealService {
	@Autowired
	public SubMealDao subMealDao;
	@Autowired
	ClientService clientService;
	@Autowired
	MealService mealService;
	@Autowired
	Meal_ClientService meal_clientService;

	public void save(SubMeal subMeal) {
		subMealDao.save(subMeal);
	}

	public SubMeal findSubMealById(SubMeal subMeal) {
		if (subMeal.getId() != null)
			return subMealDao.findSubMealById(subMeal.getId());
		return null;
	}

	public List<SubMeal> findAllSubMeals() {
		return subMealDao.findAllSubMeal();
	}

	public void update(SubMeal subMeal) {
		subMealDao.update(subMeal);
	}

	@Transactional
	public void delete(SubMeal subMeal,Client client) {
		Meal_Client meal_client=null;
		meal_client=meal_clientService.findMealClientBySubMealClientId(client, subMeal);
		if(meal_client != null)
		{
			meal_clientService.delete(meal_client);
			subMealDao.delete(subMeal);
		}
		
		
	}

	public List<SubMeal> findSubMeals(Integer clientId, Integer mealNumber) {
		List<SubMeal> result = null;
		Client client = clientService.findClientById(clientId);
		Meal meal = mealService.findMealById(mealNumber);
		if (client != null && meal != null)
			result = subMealDao.getSubMeals(client, meal);
		return result;
	}

}
