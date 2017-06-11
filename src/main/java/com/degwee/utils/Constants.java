package com.degwee.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Constants {
	public static final Integer toCut=0;
	public static final Integer toBulk=1;
	public static final Integer deductedRateFromTEE=20;//20%
	public static final Integer mealOne=1;
	public static final Integer mealTwo=2;
	public static final Integer mealThree=3;
	public static final Integer mealFour=4;
	public static final Integer maxMealSize=4;
	public static final Integer standardIngedientsPortion=100;//100 gm
	public static final String calculatedProtien="protien";
	public static final String calculatedCarbs="carbs";
	public static final String calculatedFats="fats";
	public static final String calculatedTotalCalories="totalCalories";
	public static final int createMode=0;
	public static final int editMode=0;
	public static final String AUTH_KEY="app.user.name";
	public static Double getLBMperPound(Double LBM)
	{
		return LBM/0.45;
	}
	
	public static void showMessage(String message,boolean error)
	{
		if(error)
		{
			FacesMessage facesMessage = new FacesMessage(message);
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		else
		{
			FacesMessage facesMessage = new FacesMessage(message);
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
	}
	
	public static double roundDouble(double value,int places)
	{
		 if (places < 0) throw new IllegalArgumentException();

		    BigDecimal bdValue = new BigDecimal(value);
		    bdValue = bdValue.setScale(places, RoundingMode.HALF_UP);
		    return bdValue.doubleValue();
	}

}
