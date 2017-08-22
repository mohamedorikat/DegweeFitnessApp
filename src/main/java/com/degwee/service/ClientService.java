package com.degwee.service;

import com.degwee.model.Client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.daos.ClientDao;

@Service
public class ClientService {

	@Autowired
	public ClientDao clientDao;
	@Autowired
	public ClientNutritionInfoService clientNutritionService;

	@Transactional
	public boolean save(Client client) {
		Client existedClient = null;
		existedClient = clientDao.findClientByEmail(client.getEmail());
		if (existedClient != null) {
			return true;
		} else {
			clientNutritionService.save(client.getNutritionInfo());
			clientDao.save(client);
		}
		return false;
	}

	public Client findClientById(Integer clientid) {
		if (clientid != null)
			return clientDao.findClientById(clientid);
		return null;
	}

	public List<Client> findAllClients() {
		return clientDao.findAllClients();
	}

	@Transactional
	public boolean update(Client client) {
		Client existedClient = null;
		existedClient = clientDao.findClientByEmail(client.getEmail());
		if (existedClient != null && existedClient.getClientId() != client.getClientId()) {
			//client already exist with this email
			return true;
		} else {
		clientNutritionService.update(client.getNutritionInfo());
		clientDao.update(client);
		return false;
		}
	}

	public void delete(Client client) {
		clientDao.delete(client);
	}

}
