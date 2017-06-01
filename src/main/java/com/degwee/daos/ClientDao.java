package com.degwee.daos;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Client;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class ClientDao extends CustomHibernateDaoSupport {

	
	@Transactional
	public void save(Client client) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(client);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void update(Client client) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.update(client);
				session.flush();
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Client client) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(client);
				session.flush();
				return null;
			}
		});
	}

	public Client findClientById(Integer id) {
		Client client = null;
		List singleList = getHibernateTemplate().find("from Client a where a.ClientId=?", id);
		if (singleList != null)
			client = (Client) singleList.get(0);
		return client;

	}

	public List<Client> findAllClients() {
		List<Client> clients = (List<Client>) getHibernateTemplate().find("from Client");
		return clients;

	}

	public Client findClientByEmail(String email) {
		Client client = null;
		List singleList = getHibernateTemplate().find("from Client a where a.email like ?", email);
		if (singleList != null && !singleList.isEmpty())
			client = (Client) singleList.get(0);
		return client;

	}

}
