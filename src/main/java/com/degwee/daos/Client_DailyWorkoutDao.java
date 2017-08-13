package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.degwee.model.Client_DailyWorkout;
import com.degwee.model.Client;
import com.degwee.model.Daily_Workout;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class Client_DailyWorkoutDao extends CustomHibernateDaoSupport {

	@Transactional
	public void save(Client_DailyWorkout client_DailyWorkout) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(client_DailyWorkout);
	}

	@Transactional
	public void update(Client_DailyWorkout client_DailyWorkout) {
		getHibernateTemplate().update(client_DailyWorkout);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(Client_DailyWorkout client_DailyWorkout) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(client_DailyWorkout);
				session.flush();
				return null;
			}
		});
	}

	public Client_DailyWorkout findClient_DailyWorkoutById(Integer id) {
		Client_DailyWorkout client_DailyWorkout = null;
		List singleList = getHibernateTemplate().find("from Client_DailyWorkout a where a.id=?", id);
		if (singleList != null)
			client_DailyWorkout = (Client_DailyWorkout) singleList.get(0);
		return client_DailyWorkout;

	}

	public List<Client_DailyWorkout> findAllClient_DailyWorkout() {
		List<Client_DailyWorkout> client_DailyWorkoutList = (List<Client_DailyWorkout>) getHibernateTemplate()
				.find("from Client_DailyWorkout");
		return client_DailyWorkoutList;

	}

	public Client_DailyWorkout findClientDailyWorkoutByClientDailyWorkout(Client client, Daily_Workout dailyWorkout) {
		Client_DailyWorkout client_DailyWorkout = null;
		List singleList = getHibernateTemplate()
				.find("from Client_DailyWorkout a where a.client=? AND a.daily_Workout=?", client, dailyWorkout);
		if (singleList != null)
			client_DailyWorkout = (Client_DailyWorkout) singleList.get(0);
		return client_DailyWorkout;

	}

	public List<Client_DailyWorkout> findClientDailyWorkoutByClientId(int clientId) {
		List<Client_DailyWorkout> client_DailyWorkoutList = (List<Client_DailyWorkout>) getHibernateTemplate()
				.find("from Client_DailyWorkout a where a.client.ClientId=?", clientId);
		return client_DailyWorkoutList;

	}

}
