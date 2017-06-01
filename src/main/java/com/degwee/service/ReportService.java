package com.degwee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.degwee.daos.ReportDao;
import com.degwee.model.Report;

@Service
public class ReportService {
	@Autowired
	public ReportDao reportDao;

	public void save(Report report) {
		reportDao.save(report);
	}

	public Report findReportById(Report report) {
		if (report.getId() != null)
			return reportDao.findReportById(report.getId());
		return null;
	}
	public List<Report> findAllReports()
	{
		return reportDao.findAllReport();
	}
	public void update(Report report )
	{
		 reportDao.update(report);
	}
	public void delete(Report report )
	{
		 reportDao.delete(report);
	}
}
