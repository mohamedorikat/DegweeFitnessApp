package com.degwee.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.degwee.model.FolderPath;
import com.degwee.model.JasperParam;
import com.degwee.model.Meal;
import com.degwee.model.Report;
import com.degwee.model.StandardIngerdients;
import com.degwee.model.SubMeal;
import com.degwee.service.FolderPathService;
import com.degwee.service.MealService;
import com.degwee.service.Meal_ClientService;
import com.degwee.service.ReportService;
import com.degwee.service.StandardIngerdientsService;
import com.degwee.service.SubMealService;
import com.degwee.utils.Constants;
import com.degwee.utils.FileManger;
import com.degwee.utils.JasperIntegration;

@ManagedBean(name = "mealBean")
@SessionScoped
public class MealBean {
	@Autowired
	StandardIngerdientsService ingerdientsService;
	@Autowired
	MealService mealService;
	@ManagedProperty(value = "#{clientBean}")
	ClientBean clientBean;
	@Autowired
	Meal_ClientService meal_ClientService;
	@Autowired
	SubMealService subMealService;
	@Autowired
	FolderPathService folderPathService;
	@Autowired
	JasperIntegration jasperIntegration;
	@Autowired
	ReportService reportService;
	private List<Meal> mealList;

	private List<StandardIngerdients> allIngerdients;
	private List<SubMeal> meal1;
	private List<SubMeal> meal2;
	private List<SubMeal> meal3;
	private List<SubMeal> meal4;

	private List<Meal> selectMealsList;
	private SubMeal selectedSubMeal = null;
	private StandardIngerdients selectedStandardIngerdients = null;
	private FileManger fileManager = null;
	private boolean generateReportEnabled = false;

	private double proteinCounter1 = 0.0;
	private double carbsCounter1 = 0.0;
	private double fatsnCounter1 = 0.0;
	private double totalCounter1 = 0.0;

	private double proteinCounter2 = 0.0;
	private double carbsCounter2 = 0.0;
	private double fatsnCounter2 = 0.0;
	private double totalCounter2 = 0.0;

	private double proteinCounter3 = 0.0;
	private double carbsCounter3 = 0.0;
	private double fatsnCounter3 = 0.0;
	private double totalCounter3 = 0.0;

	private double proteinCounter4 = 0.0;
	private double carbsCounter4 = 0.0;
	private double fatsnCounter4 = 0.0;
	private double totalCounter4 = 0.0;

	private double targetProteinCounter = 0.0;
	private double targetCarbsCounter = 0.0;
	private double targetFatsnCounter = 0.0;
	private double targettotalCounter = 0.0;

	private double mealStatProtein = 0.0;
	private double mealStatCarbs = 0.0;
	private double mealStatFats = 0.0;
	private double mealStatTotalCalories = 0.0;

	public double getProteinCounter1() {
		return proteinCounter1;
	}

	public void setProteinCounter1(double proteinCounter1) {
		this.proteinCounter1 = proteinCounter1;
	}

	public double getCarbsCounter1() {
		return carbsCounter1;
	}

	public void setCarbsCounter1(double carbsCounter1) {
		this.carbsCounter1 = carbsCounter1;
	}

	public double getFatsnCounter1() {
		return fatsnCounter1;
	}

	public void setFatsnCounter1(double fatsnCounter1) {
		this.fatsnCounter1 = fatsnCounter1;
	}

	public double getTotalCounter1() {
		return totalCounter1;
	}

	public void setTotalCounter1(double totalCounter1) {
		this.totalCounter1 = totalCounter1;
	}

	public double getProteinCounter2() {
		return proteinCounter2;
	}

	public void setProteinCounter2(double proteinCounter2) {
		this.proteinCounter2 = proteinCounter2;
	}

	public double getCarbsCounter2() {
		return carbsCounter2;
	}

	public void setCarbsCounter2(double carbsCounter2) {
		this.carbsCounter2 = carbsCounter2;
	}

	public double getFatsnCounter2() {
		return fatsnCounter2;
	}

	public void setFatsnCounter2(double fatsnCounter2) {
		this.fatsnCounter2 = fatsnCounter2;
	}

	public double getTotalCounter2() {
		return totalCounter2;
	}

	public void setTotalCounter2(double totalCounter2) {
		this.totalCounter2 = totalCounter2;
	}

	public double getProteinCounter3() {
		return proteinCounter3;
	}

	public void setProteinCounter3(double proteinCounter3) {
		this.proteinCounter3 = proteinCounter3;
	}

	public double getCarbsCounter3() {
		return carbsCounter3;
	}

	public void setCarbsCounter3(double carbsCounter3) {
		this.carbsCounter3 = carbsCounter3;
	}

	public double getFatsnCounter3() {
		return fatsnCounter3;
	}

	public void setFatsnCounter3(double fatsnCounter3) {
		this.fatsnCounter3 = fatsnCounter3;
	}

	public double getTotalCounter3() {
		return totalCounter3;
	}

	public void setTotalCounter3(double totalCounter3) {
		this.totalCounter3 = totalCounter3;
	}

	public double getProteinCounter4() {
		return proteinCounter4;
	}

	public void setProteinCounter4(double proteinCounter4) {
		this.proteinCounter4 = proteinCounter4;
	}

	public double getCarbsCounter4() {
		return carbsCounter4;
	}

	public void setCarbsCounter4(double carbsCounter4) {
		this.carbsCounter4 = carbsCounter4;
	}

	public double getFatsnCounter4() {
		return fatsnCounter4;
	}

	public void setFatsnCounter4(double fatsnCounter4) {
		this.fatsnCounter4 = fatsnCounter4;
	}

	public double getTotalCounter4() {
		return totalCounter4;
	}

	public void setTotalCounter4(double totalCounter4) {
		this.totalCounter4 = totalCounter4;
	}

	public double getTargetProteinCounter() {
		return targetProteinCounter;
	}

	public void setTargetProteinCounter(double targetProteinCounter) {
		this.targetProteinCounter = targetProteinCounter;
	}

	public double getTargetCarbsCounter() {
		return targetCarbsCounter;
	}

	public void setTargetCarbsCounter(double targetCarbsCounter) {
		this.targetCarbsCounter = targetCarbsCounter;
	}

	public double getTargetFatsnCounter() {
		return targetFatsnCounter;
	}

	public void setTargetFatsnCounter(double targetFatsnCounter) {
		this.targetFatsnCounter = targetFatsnCounter;
	}

	public double getTargettotalCounter() {
		return targettotalCounter;
	}

	public void setTargettotalCounter(double targettotalCounter) {
		this.targettotalCounter = targettotalCounter;
	}

	public ClientBean getClientBean() {
		return clientBean;
	}

	public void setClientBean(ClientBean clientBean) {
		this.clientBean = clientBean;
	}

	public List<Meal> getMealList() {
		if (mealList == null || mealList.isEmpty())
			mealList = mealService.findAllMeals();
		return mealList;
	}

	public void setMealList(List<Meal> mealList) {
		this.mealList = mealList;
	}

	public List<SubMeal> getMeal1() {
		return meal1;
	}

	public void setMeal1(List<SubMeal> meal1) {
		this.meal1 = meal1;
	}

	public List<SubMeal> getMeal2() {
		return meal2;
	}

	public void setMeal2(List<SubMeal> meal2) {
		this.meal2 = meal2;
	}

	public List<SubMeal> getMeal3() {
		return meal3;
	}

	public void setMeal3(List<SubMeal> meal3) {
		this.meal3 = meal3;
	}

	public List<SubMeal> getMeal4() {
		return meal4;
	}

	public void setMeal4(List<SubMeal> meal4) {
		this.meal4 = meal4;
	}

	public SubMeal getSelectedSubMeal() {
		return selectedSubMeal;
	}

	public void setSelectedSubMeal(SubMeal selectedSubMeal) {
		this.selectedSubMeal = selectedSubMeal;
	}

	public boolean isGenerateReportEnabled() {
		return generateReportEnabled;
	}

	public void setGenerateReportEnabled(boolean generateReportEnabled) {
		this.generateReportEnabled = generateReportEnabled;
	}

	public List<StandardIngerdients> getAllIngerdients() {
		generateReportEnabled = false;
		allIngerdients = ingerdientsService.findAllStandardIngerdientss();
		return allIngerdients;
	}

	public void setAllIngerdients(List<StandardIngerdients> allIngerdients) {
		this.allIngerdients = allIngerdients;
	}

	public List<Meal> getSelectMealsList() {
		if (selectMealsList == null || selectMealsList.isEmpty())
			selectMealsList = mealService.findAllMeals();
		return selectMealsList;
	}

	public void setSelectMealsList(List<Meal> selectMealsList) {
		this.selectMealsList = selectMealsList;
	}

	public StandardIngerdients getSelectedStandardIngerdients() {
		return selectedStandardIngerdients;
	}

	public void setSelectedStandardIngerdients(StandardIngerdients selectedStandardIngerdients) {
		this.selectedStandardIngerdients = selectedStandardIngerdients;
	}

	public double getMealStatProtein() {
		return mealStatProtein;
	}

	public void setMealStatProtein(double mealStatProtein) {
		this.mealStatProtein = mealStatProtein;
	}

	public double getMealStatCarbs() {
		return mealStatCarbs;
	}

	public void setMealStatCarbs(double mealStatCarbs) {
		this.mealStatCarbs = mealStatCarbs;
	}

	public double getMealStatFats() {
		return mealStatFats;
	}

	public void setMealStatFats(double mealStatFats) {
		this.mealStatFats = mealStatFats;
	}

	public double getMealStatTotalCalories() {
		return mealStatTotalCalories;
	}

	public void setMealStatTotalCalories(double mealStatTotalCalories) {
		this.mealStatTotalCalories = mealStatTotalCalories;
	}

	public FileManger getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManger fileManager) {
		this.fileManager = fileManager;
	}

	public void showIngerdientsToMealDialog(StandardIngerdients selectedIngedrients) {
		selectedSubMeal = new SubMeal();
		selectedSubMeal.setMeal(new Meal());
		if (selectedIngedrients.getId() == null) {
			Constants.showMessage("Error in Adding To Meal List Contact Orikat", true);
			return;
		}
		selectedSubMeal.setStandardIngerdients(selectedIngedrients);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('addToMealDialog').show()");
	}

	private Meal getSelectedMealFromDialog() {
		Meal meal = null;
		if (selectedSubMeal.getMeal().getId() != null) {
			meal = mealService.findMealById(selectedSubMeal.getMeal().getId());
		} else {
			FacesMessage facesMessage = new FacesMessage("Please Select Meal First To Add");
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
		return meal;
	}

	public void addIngerdientsToMeal() {

		selectedSubMeal.setMeal(getSelectedMealFromDialog());
		if (selectedSubMeal.getMeal() != null) {
			if (selectedSubMeal.getMeal().getNumber() != null) {
				if (selectedSubMeal.getMeal().getNumber().equals(Constants.mealOne)) {
					meal1.add(selectedSubMeal);
					updateNutritionInfoCounters(Constants.mealOne, meal1);
				}
				if (selectedSubMeal.getMeal().getNumber().equals(Constants.mealTwo)) {
					meal2.add(selectedSubMeal);
					updateNutritionInfoCounters(Constants.mealTwo, meal2);
				}
				if (selectedSubMeal.getMeal().getNumber().equals(Constants.mealThree)) {
					meal3.add(selectedSubMeal);
					updateNutritionInfoCounters(Constants.mealThree, meal3);
				}
			}
			if (selectedSubMeal.getMeal().getNumber().equals(Constants.mealFour)) {
				meal4.add(selectedSubMeal);
				updateNutritionInfoCounters(Constants.mealFour, meal4);
			}
		}
	}

	public void updateNutritionInfoCounters(Integer mealNumber, List<SubMeal> mealList) {
		Map<String, Double> result = null;
		result = mealService.calculateMealNutritionInfo(mealList);
		if (result != null) {
			if (mealNumber.equals(Constants.mealOne)) {

				proteinCounter1 = result.get(Constants.calculatedProtien);
				carbsCounter1 = result.get(Constants.calculatedCarbs);
				fatsnCounter1 = result.get(Constants.calculatedFats);
				totalCounter1 = result.get(Constants.calculatedTotalCalories);
			} else if (mealNumber.equals(Constants.mealTwo)) {

				proteinCounter2 = result.get(Constants.calculatedProtien);
				carbsCounter2 = result.get(Constants.calculatedCarbs);
				fatsnCounter2 = result.get(Constants.calculatedFats);
				totalCounter2 = result.get(Constants.calculatedTotalCalories);
			} else if (mealNumber.equals(Constants.mealThree)) {

				proteinCounter3 = result.get(Constants.calculatedProtien);
				carbsCounter3 = result.get(Constants.calculatedCarbs);
				fatsnCounter3 = result.get(Constants.calculatedFats);
				totalCounter3 = result.get(Constants.calculatedTotalCalories);
			} else if (mealNumber.equals(Constants.mealFour)) {

				proteinCounter4 = result.get(Constants.calculatedProtien);
				carbsCounter4 = result.get(Constants.calculatedCarbs);
				fatsnCounter4 = result.get(Constants.calculatedFats);
				totalCounter4 = result.get(Constants.calculatedTotalCalories);
			}
			this.updateTotalMealStats();
		}
	}

	public String saveMeals() {
		try {
			meal_ClientService.saveMealClient_SubMeals(meal1, meal2, meal3, meal4, clientBean.getClient());
			generateReport();
			Constants.showMessage("Report Generated Successfully", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private boolean checkMealWillExceedSize(List<SubMeal> meal) {
		if (meal.size() < Constants.maxMealSize)
			return false;
		else
			return true;
	}

	public void removeSubMealFromMealList1(SubMeal subMeal) {
		if (meal1 != null && !meal1.isEmpty()) {
			if (subMeal.getId() != null)
				subMealService.delete(subMeal, clientBean.getClient());
			meal1.remove(subMeal);
			updateNutritionInfoCounters(Constants.mealOne, meal1);
		}
	}

	public void removeSubMealFromMealList2(SubMeal subMeal) {
		if (meal2 != null && !meal2.isEmpty()) {
			if (subMeal.getId() != null)
				subMealService.delete(subMeal, clientBean.getClient());
			meal2.remove(subMeal);
			updateNutritionInfoCounters(Constants.mealTwo, meal2);
		}
	}

	public void removeSubMealFromMealList3(SubMeal subMeal) {
		if (meal3 != null && !meal3.isEmpty()) {
			if (subMeal.getId() != null)
				subMealService.delete(subMeal, clientBean.getClient());
			meal3.remove(subMeal);
			updateNutritionInfoCounters(Constants.mealThree, meal3);
		}
	}

	public void removeSubMealFromMealList4(SubMeal subMeal) {
		if (meal4 != null && !meal4.isEmpty()) {
			if (subMeal.getId() != null)
				subMealService.delete(subMeal, clientBean.getClient());
			meal4.remove(subMeal);
			updateNutritionInfoCounters(Constants.mealFour, meal4);
		}
	}

	public String initMealSelection(boolean goToMealSelection) {
		if (goToMealSelection) {
			meal1 = new ArrayList<>();
			meal2 = new ArrayList<>();
			meal3 = new ArrayList<>();
			meal4 = new ArrayList<>();
			if (clientBean.mode == Constants.editMode) {
				meal1 = subMealService.findSubMeals(clientBean.getClient().getClientId(), Constants.mealOne);
				updateNutritionInfoCounters(Constants.mealOne, meal1);
				meal2 = subMealService.findSubMeals(clientBean.getClient().getClientId(), Constants.mealTwo);
				updateNutritionInfoCounters(Constants.mealTwo, meal2);
				meal3 = subMealService.findSubMeals(clientBean.getClient().getClientId(), Constants.mealThree);
				updateNutritionInfoCounters(Constants.mealThree, meal3);
				meal4 = subMealService.findSubMeals(clientBean.getClient().getClientId(), Constants.mealFour);
				updateNutritionInfoCounters(Constants.mealFour, meal4);
			}

			if (clientBean.getClient().getNutritionInfo().getGoal().equals(Constants.toBulk)) {
				targetCarbsCounter = clientBean.getClient().getNutritionInfo().getCarbsToBulk();
				targetFatsnCounter = clientBean.getClient().getNutritionInfo().getFatsToBulk();
				targetProteinCounter = clientBean.getClient().getNutritionInfo().getProteinToBulk();
			} else {
				targetCarbsCounter = clientBean.getClient().getNutritionInfo().getCarbsTocut();
				targetFatsnCounter = clientBean.getClient().getNutritionInfo().getFatsToCut();
				targetProteinCounter = clientBean.getClient().getNutritionInfo().getProteinToCut();
			}

			return "mealSelection";
		}

		else
			return null;
	}

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
		generateReportEnabled = false;

	}

	public void showAddIngerdientsDialog() {
		selectedStandardIngerdients = new StandardIngerdients();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('AddIngerdientsDialog').show()");

	}

	public void deleteIngerdient() {
		if (selectedStandardIngerdients != null) {
			try {
				ingerdientsService.delete(selectedStandardIngerdients);
				allIngerdients.remove(selectedStandardIngerdients);
			} catch (Exception ex) {
				Constants.showMessage("Please Remove all SubMeals linked To this Ingerdient Before Delete", true);
			}
		} else {
			Constants.showMessage("Please Select Ingerdient First To Delete", true);
		}
	}

	public void saveIngerdients() {
		try {
			if (selectedStandardIngerdients != null) {
				if (selectedStandardIngerdients.getFolderPath() == null) {
					Constants.showMessage("Please Choose Image For Food Before Save", true);
					return;
				}
				ingerdientsService.save(selectedStandardIngerdients);
				allIngerdients.add(selectedStandardIngerdients);
				Constants.showMessage("Ingerdient Added Successfully", false);
			} else {
				Constants.showMessage("Contact Orikat Null Ingerdient while saving", true);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			Constants.showMessage("Error in adding inegrdient," + ex.getMessage(), true);
		}
	}

	public void resetIngerdient() {
		selectedStandardIngerdients = new StandardIngerdients();
	}

	public void handleFileUpload(FileUploadEvent event) {

		fileManager = new FileManger(event.getFile());
		FolderPath folderPath = folderPathService.findFolderPathById(1);
		;
		if (folderPath == null) {
			Constants.showMessage("Contact orikat add the data in folder table", true);
			return;
		}
		int result = fileManager.uploadFile(folderPath);
		switch (result) {
		case 0:
			break;
		case 1:
			Constants.showMessage("Image Already Exist with this Name,Please Rename the Image", true);
			return;
		case 3:
			Constants.showMessage("NO file selected to upload contact orikat.", true);
			return;
		default:
			Constants.showMessage("Error Happened While Uploading Image retry again.", true);
			return;
		}
		selectedStandardIngerdients.setFolderPath(folderPath);
		selectedStandardIngerdients.setImageName(fileManager.getUploadedFile().getFileName());
	}

	private void updateTotalMealStats() {
		mealStatProtein = proteinCounter1 + proteinCounter2 + proteinCounter3 + proteinCounter4;
		mealStatFats = fatsnCounter1 + fatsnCounter2 + fatsnCounter3 + fatsnCounter4;
		mealStatCarbs = carbsCounter1 + carbsCounter2 + carbsCounter3 + carbsCounter4;
		mealStatTotalCalories = totalCounter1 + totalCounter2 + totalCounter3 + totalCounter4;

		mealStatProtein = Constants.roundDouble(mealStatProtein, 1);
		mealStatFats = Constants.roundDouble(mealStatFats, 1);
		mealStatCarbs = Constants.roundDouble(mealStatCarbs, 1);
		mealStatTotalCalories = Constants.roundDouble(mealStatTotalCalories, 1);

	}

	public void generateReport() throws Exception {
		String[] generatedPDFName = null;
		Report report = null;
		try {

			if (clientBean.getClient() == null) {
				Constants.showMessage("Client Null in report", true);
				return;
			}
			JasperParam jasperObj = new JasperParam();
			jasperObj.setTotalMealStatsProteins(mealStatProtein);
			jasperObj.setTotalMealStatsFats(mealStatFats);
			jasperObj.setTotalMealStatsCarbs(mealStatCarbs);
			jasperObj.setTotalMealStatsCalories(mealStatTotalCalories);
			jasperObj.setClient(clientBean.getClient());
			jasperObj.setJasperXmlFolder(folderPathService.findFolderPathById(2));
			jasperObj.setJasperReportFolder(folderPathService.findFolderPathById(3));
			generatedPDFName = jasperIntegration.generateReport(jasperObj);
			if (generatedPDFName != null) {
				downloadReport(generatedPDFName[0], generatedPDFName[1]);
			}
			System.out.println("Done Download");

			// report=new Report();
			// report.setClient(clientBean.getClient());
			// report.setFolderPath(folderPathService.findFolderPathById(3));
			// report.setReportName(generatedPDFName);
			// report.setReportDate(new Date());
			// reportService.save(report);
			//

		} catch (Exception e) {
			System.out.println(e);
			Constants.showMessage("Error while Generating Rpeort," + e.getMessage(), true);
		}

		return;
	}

	private boolean downloadReport(String fileName, String fullPath) throws Exception {
		StreamedContent content = null;
		boolean result = false;
		if (fileManager == null) {
			fileManager = new FileManger(null);
		}
		try {
			if (fileName != null && fullPath != null) {
				content = fileManager.handleFiledownload(fileName, fullPath);
				fileManager.setDownloadedFile(content);
				result = true;
			} else {
				throw new Exception("error while download no file name or file path");
			}
		} catch (Exception ex) {
			Constants.showMessage("Error while Downloading Rpeort," + ex.getMessage(), true);
		}
		return result;
	}

}
