package com.degwee.utils;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.ejb.FinderException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.degwee.model.Client;
import com.degwee.model.ClientNutritionInfo;
import com.degwee.service.ClientService;

public class Main {

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
		String s = System.getProperty("user.dir");
		Path imagesPath=Paths.get("\\Users\\orikat\\Documents\\DegweeWorkspace\\NutritionFitnessApp\\src\\main\\resources\\ingerdient_images\\").resolve("1.jpg");
		System.out.println(Files.exists(imagesPath,LinkOption.NOFOLLOW_LINKS));
		ClientService clientService = (ClientService) appContext.getBean("clientService");
		Client c = new Client();
		c.setEmail("test");
		c.setFirstName("test");
		c.setGender("M");
		ClientNutritionInfo cf = new ClientNutritionInfo();
		cf.setActivityRate(3.0f);
		cf.setBMR(123.00);
		cf.setBodyFat(0);
		cf.setGoal(1);
		cf.setHeight(0);
		cf.setLeanMass(1.0);
		cf.setTACN(123.00);
		cf.setTEE(123.00);
		cf.setWeight(12.0);
		c.setNutritionInfo(cf);

		c = new Client();
		c.setClientId(1);
		// clientService.findAllClients();
		c = clientService.findClientById(1);
		System.out.println("done," + c.getFirstName());

	}

}
