package com.degwee.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.degwee.model.Client;
import com.degwee.model.JasperParam;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Component
public class JasperIntegration {
	@Autowired
	DriverManagerDataSource datasource;

	public String[] generateReport(JasperParam jasperParamObj) throws JRException,Exception {
		HashMap parameters = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh_mm_ss");
		String[] fileParams = null;
		try {
			String jrxmlFileName = jasperParamObj.getJasperXmlFolder().getFolderPath() + "DegweeMealsReport.jrxml";
			String jasperFileName = jasperParamObj.getJasperXmlFolder().getFolderPath() + "DegweeMealsReport.jasper";
			String fileName = jasperParamObj.getClient().getFirstName() + "_" + jasperParamObj.getClient().getLastName()
					+ "_" + sdf.format(new Date()) + ".pdf";

			String pdfFileName = jasperParamObj.getJasperReportFolder().getFolderPath() + fileName;
			System.out.println(jrxmlFileName);
			System.out.println(jasperFileName);
			System.out.println(pdfFileName);
			System.out.println("Before Compiling");

			JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
			System.out.println("After Compiling");
			
			// Generate jasper print
			@SuppressWarnings("unchecked")
			JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, parameters,
					datasource.getConnection());
			System.out.println("------------------- before export");
			// Export pdf file
			JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);
			fileParams = new String[2];
			fileParams[0] = fileName;
			fileParams[1] = pdfFileName;
			System.out.println("Done exporting reports to pdf");

		} catch (JRException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return fileParams;
	}

}
