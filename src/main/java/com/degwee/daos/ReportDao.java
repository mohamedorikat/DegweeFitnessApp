package com.degwee.daos;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.degwee.model.Report;
import com.degwee.utils.CustomHibernateDaoSupport;

@Repository
public class ReportDao extends CustomHibernateDaoSupport {
	@Transactional
	public void save(Report report) {
		getHibernateTemplate().setCheckWriteOperations(false);
		getHibernateTemplate().save(report);
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void update(Report report) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				session.update(report);
				session.flush();
				return null;
			}
		});
	}

	public void delete(Report report) {
		getHibernateTemplate().delete(report);
	}

	public Report findReportById(Integer id) {
		Report report = null;
		List singleList = getHibernateTemplate().find("from Report a where a.Id=?", id);
		if (singleList != null)
			report = (Report) singleList.get(0);
		return report;

	}

	public List<Report> findAllReport() {
		List<Report> reportList = (List<Report>) getHibernateTemplate().find("from Report");
		return reportList;

	}
}
