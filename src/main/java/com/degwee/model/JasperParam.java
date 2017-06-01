package com.degwee.model;

public class JasperParam {
private Double totalMealStatsProteins=null;
private Double totalMealStatsFats=null;
private Double totalMealStatsCarbs=null;
private Double totalMealStatsCalories=null;
private FolderPath jasperXmlFolder=null;
private FolderPath jasperReportFolder=null;
private Client client=null;


public Double getTotalMealStatsProteins() {
	return totalMealStatsProteins;
}
public void setTotalMealStatsProteins(Double totalMealStatsProteins) {
	this.totalMealStatsProteins = totalMealStatsProteins;
}
public Double getTotalMealStatsFats() {
	return totalMealStatsFats;
}
public void setTotalMealStatsFats(Double totalMealStatsFats) {
	this.totalMealStatsFats = totalMealStatsFats;
}
public Double getTotalMealStatsCarbs() {
	return totalMealStatsCarbs;
}
public void setTotalMealStatsCarbs(Double totalMealStatsCarbs) {
	this.totalMealStatsCarbs = totalMealStatsCarbs;
}
public Double getTotalMealStatsCalories() {
	return totalMealStatsCalories;
}
public void setTotalMealStatsCalories(Double totalMealStatsCalories) {
	this.totalMealStatsCalories = totalMealStatsCalories;
}
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}
public FolderPath getJasperXmlFolder() {
	return jasperXmlFolder;
}
public void setJasperXmlFolder(FolderPath jasperXmlFolder) {
	this.jasperXmlFolder = jasperXmlFolder;
}
public FolderPath getJasperReportFolder() {
	return jasperReportFolder;
}
public void setJasperReportFolder(FolderPath jasperReportFolder) {
	this.jasperReportFolder = jasperReportFolder;
}



}
