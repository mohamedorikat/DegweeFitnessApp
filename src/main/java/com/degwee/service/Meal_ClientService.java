package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.daos.Meal_ClientDao;
import com.degwee.model.Client;
import com.degwee.model.Meal_Client;
import com.degwee.model.SubMeal;

@Service
public class Meal_ClientService {
	@Autowired
	public Meal_ClientDao meal_ClientDao;
	@Autowired 
	SubMealService subMealService;

	public void save(Meal_Client meal_Client) {
		meal_ClientDao.save(meal_Client);
	}

	public Meal_Client findMeal_ClientById(Meal_Client meal_Client) {
		if (meal_Client.getId() != null)
			return meal_ClientDao.findMeal_ClientById(meal_Client.getId());
		return null;
	}
	public List<Meal_Client> findAllMeal_Clients()
	{
		return meal_ClientDao.findAllMeal_Client();
	}
	public void update(Meal_Client meal_Client )
	{
		 meal_ClientDao.update(meal_Client);
	}
	public void delete(Meal_Client meal_Client )
	{
		 meal_ClientDao.delete(meal_Client);
	}
	public Meal_Client findMealClientBySubMealClientId(Client client,SubMeal subMeal)
	{
		return meal_ClientDao.findMealClientBySubMealClient(client, subMeal);
	}
	@Transactional
	public void saveMealClient_SubMeals(List<SubMeal> meal1,List<SubMeal> meal2,List<SubMeal> meal3,List<SubMeal> meal4,Client client)
	{
		Meal_Client mealClient=null;
		for(SubMeal subMeal:meal1)
		{
			if(subMeal.getId() != null)
				continue;
			mealClient=new Meal_Client();
			mealClient.setClient(client);
			subMealService.save(subMeal);
			mealClient.setSubMeal(subMeal);
			this.save(mealClient);
		}
		mealClient=null;
		for(SubMeal subMeal:meal2)
		{
			if(subMeal.getId() != null)
				continue;
			mealClient=new Meal_Client();
			mealClient.setClient(client);
			subMealService.save(subMeal);
			mealClient.setSubMeal(subMeal);
			this.save(mealClient);
		}
		mealClient=null;
		for(SubMeal subMeal:meal3)
		{
			if(subMeal.getId() != null)
				continue;
			mealClient=new Meal_Client();
			mealClient.setClient(client);
			subMealService.save(subMeal);
			mealClient.setSubMeal(subMeal);
			this.save(mealClient);
		}
		mealClient=null;
		for(SubMeal subMeal:meal4)
		{
			if(subMeal.getId() != null)
				continue;
			mealClient=new Meal_Client();
			mealClient.setClient(client);
			subMealService.save(subMeal);
			mealClient.setSubMeal(subMeal);
			this.save(mealClient);
		}
	}
}
