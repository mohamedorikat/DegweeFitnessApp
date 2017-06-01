package com.degwee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.degwee.daos.MealDao;
import com.degwee.model.Meal;
import com.degwee.model.SubMeal;
import com.degwee.utils.Constants;

@Service
public class MealService {
	@Autowired
	public MealDao mealDao;

	public void save(Meal meal) {
		mealDao.save(meal);
	}

	public Meal findMealById(Integer mealId) {
		if (mealId != null)
			return mealDao.findMealById(mealId);
		return null;
	}

	public List<Meal> findAllMeals() {
		return mealDao.findAllMeal();
	}

	public void update(Meal meal) {
		mealDao.update(meal);
	}

	public void delete(Meal meal) {
		mealDao.delete(meal);
	}

	public Map<String, Double> calculateMealNutritionInfo(List<SubMeal> mealList) {
		Double proteinCounter = 0.0, carbsCounter = 0.0, fatsCounter = 0.0, totalCalories = 0.0;
		Map<String, Double> nutritionInfoAfterMealSelection = new HashMap<>();
		if (mealList == null || mealList.isEmpty()) {
			nutritionInfoAfterMealSelection.put(Constants.calculatedProtien, proteinCounter);
			nutritionInfoAfterMealSelection.put(Constants.calculatedCarbs, carbsCounter);
			nutritionInfoAfterMealSelection.put(Constants.calculatedFats, fatsCounter);
			nutritionInfoAfterMealSelection.put(Constants.calculatedTotalCalories, totalCalories);
			return nutritionInfoAfterMealSelection;
		} else {
			for (SubMeal meal : mealList) {
				proteinCounter += (meal.getStandardIngerdients().getContainedProtiens() * meal.getIngerdientPortion())
						/ Constants.standardIngedientsPortion;
				carbsCounter += (meal.getStandardIngerdients().getContainedCarbs() * meal.getIngerdientPortion())
						/ Constants.standardIngedientsPortion;
				fatsCounter += (meal.getStandardIngerdients().getContainedFats() * meal.getIngerdientPortion())
						/ Constants.standardIngedientsPortion;
				totalCalories += (meal.getStandardIngerdients().getTotalCalories() * meal.getIngerdientPortion())
						/ Constants.standardIngedientsPortion;
			}
			nutritionInfoAfterMealSelection.put(Constants.calculatedProtien, proteinCounter);
			nutritionInfoAfterMealSelection.put(Constants.calculatedCarbs, carbsCounter);
			nutritionInfoAfterMealSelection.put(Constants.calculatedFats, fatsCounter);
			nutritionInfoAfterMealSelection.put(Constants.calculatedTotalCalories, totalCalories);
			return nutritionInfoAfterMealSelection;
		}

	}
}
