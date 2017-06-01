package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.daos.ClientNutritionInfoDao;
import com.degwee.model.Client;
import com.degwee.model.ClientNutritionInfo;
import com.degwee.utils.Constants;

@Service
public class ClientNutritionInfoService {
	@Autowired
	public ClientNutritionInfoDao clientNutritionInfoDao;
	@Autowired
	public FormulasService formulaService;

	public void save(ClientNutritionInfo clientNutitritionInfo) {
		clientNutritionInfoDao.save(clientNutitritionInfo);
	}

	
	@Transactional
	public void update(ClientNutritionInfo clientNutritionInfo) {
		clientNutritionInfoDao.update(clientNutritionInfo);
	}
	@Transactional
	public void delete(ClientNutritionInfo clientNutritionInfo) {
		clientNutritionInfoDao.delete(clientNutritionInfo);
	}

	public ClientNutritionInfo findClientNutritionInfoById(ClientNutritionInfo clientNutritionInfo) {
		if (clientNutritionInfo.getId() != null) {
			return clientNutritionInfoDao.findClientNutritionById(clientNutritionInfo.getId());
		}
		return null;
	}

	public List<ClientNutritionInfo> findAllClientNutritionInfo() {
		return clientNutritionInfoDao.findAllClientNutritionInfo();
	}

	public ClientNutritionInfo calculateNutritionInfo(ClientNutritionInfo info) {
		Double BMR, LBM, TEE, TACN, proteins, carbs, fats;
		if (info.getBodyFat() != null && info.getWeight() != null && info.getActivityRate() != null
				&& info.getGoal() != null) {
			LBM = formulaService.calculateLBM(info.getWeight(), info.getBodyFat());
			BMR = formulaService.calculateBMR(LBM);
			TEE = formulaService.calculateTEE(BMR, info.getActivityRate());
			TACN = formulaService.calculateTACN(TEE, info.getGoal());
			proteins = formulaService.calculateProteinGmFromTACN(LBM);
			fats = formulaService.calculateFatsGmFromTACN(LBM);
			carbs = formulaService.calculateCarbsGmFromTACN(proteins, fats, TACN);

			info.setLeanMass(LBM);
			info.setBMR(BMR);
			info.setTEE(TEE);
			info.setTACN(TACN);
			if (info.getGoal().equals(Constants.toCut)) {
				info.setProteinToCut(proteins);
				info.setFatsToCut(fats);
				info.setCarbsTocut(carbs);
			} else {
				info.setProteinToBulk(proteins);
				info.setFatsToBulk(fats);
				info.setCarbsToBulk(carbs);
			}
			
		}
		return info;
		
	}

}
