package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.degwee.model.ClientNutritionInfo;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class ClientNutritionInfoDao extends CustomHibernateDaoSupport {
	@Transactional
	public void save(ClientNutritionInfo clientNutritionInfo) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(clientNutritionInfo);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void update(ClientNutritionInfo clientNutritionInfo) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.update(clientNutritionInfo);
				session.flush();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void delete(ClientNutritionInfo clientNutritionInfo) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.delete(clientNutritionInfo);
				session.flush();
				return null;
			}
		});
	}

	public ClientNutritionInfo findClientNutritionById(Integer id) {
		ClientNutritionInfo clientNutritionInfo = null;
		List singleList = getHibernateTemplate().find("from ClientNutritionInfo a where a.Id=?", id);
		if (singleList != null)
			clientNutritionInfo = (ClientNutritionInfo) singleList.get(0);
		return clientNutritionInfo;

	}

	public List<ClientNutritionInfo> findAllClientNutritionInfo() {
		List<ClientNutritionInfo> clientNutritionInfoList = (List<ClientNutritionInfo>) getHibernateTemplate()
				.find("from ClientNutritionInfo");
		return clientNutritionInfoList;

	}
}
