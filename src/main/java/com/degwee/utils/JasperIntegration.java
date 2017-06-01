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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Component
public class JasperIntegration {
	@Autowired
	DriverManagerDataSource datasource;

	public String[] generateReport(JasperParam jasperParamObj) throws Exception {
		HashMap parameters = null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy hh_mm_ss");
		String[] fileParams=null;
		try {
			String jrxmlFileName = jasperParamObj.getJasperXmlFolder().getFolderPath()+"DegweeMealsReport.jrxml";			
			String jasperFileName = jasperParamObj.getJasperXmlFolder().getFolderPath()+"DegweeMealsReport.jasper";
			String fileName=jasperParamObj.getClient().getFirstName()+"_"+jasperParamObj.getClient().getLastName()+"_"+sdf.format(new Date())+".pdf";
			
			String pdfFileName = jasperParamObj.getJasperReportFolder().getFolderPath()+fileName;
			

			// String jrxmlFileName =
			// "C:/Users/salahabd/Desktop/Images/SummeryReport.jrxml";
			// String jasperFileName =
			// "C:/Users/salahabd/Desktop/Images/SummeryReport.jasper";
			// String pdfFileName =
			// "C:/Users/salahabd/Desktop/Images/SummeryReport.pdf";
			JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);

			// String dbUrl = props.getProperty("jdbc.url");
//			String dbUrl = "jdbc:oracle:thin:@localhost:1521:mydbname";
//			// String dbDriver = props.getProperty("jdbc.driver");
//			String dbDriver = "oracle.jdbc.driver.OracleDriver";
//			// String dbUname = props.getProperty("db.username");
//			String dbUname = "mydb";
//			// String dbPwd = props.getProperty("db.password");
//			String dbPwd = "mydbpw";

			// Load the JDBC driver
			// Class.forName(dbDriver);
			// Get the connection
			// Connection conn = DriverManager.getConnection(dbUrl, dbUname,
			// dbPwd);
			// Create arguments
			// Map params = new HashMap();
			
			parameters = new HashMap();
			parameters.put("proteins",jasperParamObj.getTotalMealStatsProteins().toString());
			parameters.put("fats", jasperParamObj.getTotalMealStatsFats().toString());
			parameters.put("carbohydrates", jasperParamObj.getTotalMealStatsCarbs().toString());
			parameters.put("caloires", jasperParamObj.getTotalMealStatsCalories().toString());
			parameters.put("ClientId", jasperParamObj.getClient().getClientId().toString());

			// Generate jasper print
			@SuppressWarnings("unchecked")
			JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, parameters,	datasource.getConnection());
			System.out.println("------------------- before export");
			// Export pdf file
			JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);
			fileParams=new String[2];
			fileParams[0]=fileName;
			fileParams[1]=pdfFileName;
			System.out.println("Done exporting reports to pdf");
			return fileParams;

		} catch (Exception e) {
			throw e;
		}
	}

}
