package com.degwee.service;

import org.springframework.stereotype.Component;

import com.degwee.utils.Constants;

@Component
public class FormulasService {

	public Double calculateLBM(Double weightKg, Integer bodyFat) {
		return (weightKg * (100 - bodyFat)) / 100;
	}

	public Double calculateBMR(Double LBM) {
		return (370 + (21.6 * LBM));
	}

	public Double calculateTEE(Double BMR, Float activityRate) {
		return BMR * activityRate;
	}

	public Double calculateTACN(Double TEE, Integer goal) {
		if (goal.equals(Constants.toCut))
			return (TEE * (100 - Constants.deductedRateFromTEE)) / 100;
		else
			return (TEE * (100 + Constants.deductedRateFromTEE)) / 100;

	}
	public Double calculateProteinGmFromTACN(Double LBM )
	{
		Double LBM_PerPound=Constants.getLBMperPound(LBM);
		return LBM_PerPound*1.5;
	}

	public Double calculateFatsGmFromTACN(Double LBM )
	{
		Double LBM_PerPound=Constants.getLBMperPound(LBM);
		return LBM_PerPound*0.4;
	}
	public Double calculateCarbsGmFromTACN(Double protiens,Double fats,Double TACN)
	{
		Double result=TACN-((protiens*4)+(fats*9));
		return result/4;
	}
}
