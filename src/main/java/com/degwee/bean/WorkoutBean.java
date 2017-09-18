package com.degwee.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.degwee.model.Client_DailyWorkout;
import com.degwee.model.Daily_Workout;
import com.degwee.model.Day;
import com.degwee.model.JasperParam;
import com.degwee.model.Muscle;
import com.degwee.model.Set;
import com.degwee.model.Stratgey;
import com.degwee.model.Workout;
import com.degwee.service.Client_DailyWorkoutService;
import com.degwee.service.Daily_WorkoutService;
import com.degwee.service.DayService;
import com.degwee.service.FolderPathService;
import com.degwee.service.MuscleService;
import com.degwee.service.ReportService;
import com.degwee.service.SetService;
import com.degwee.service.StratgeyService;
import com.degwee.service.WorkoutService;
import com.degwee.utils.Constants;
import com.degwee.utils.FileManger;
import com.degwee.utils.JasperIntegration;

@ManagedBean(name = "workoutBean")
@SessionScoped
public class WorkoutBean {

	@Autowired
	DayService dayService;
	@Autowired
	StratgeyService stratgeyService;
	@Autowired
	WorkoutService workoutService;
	@Autowired
	MuscleService muscleService;
	@Autowired
	SetService setService;
	@Autowired
	Daily_WorkoutService dailyWorkoutService;
	@Autowired
	Client_DailyWorkoutService clientDailyWorkoutService;
	@Autowired
	FolderPathService folderPathService;
	@Autowired
	JasperIntegration jasperIntegration;
	@Autowired
	ReportService reportService;

	@ManagedProperty(value = "#{clientBean}")
	ClientBean clientBean;

	List<Daily_Workout> dayOneWorkout;
	List<Daily_Workout> dayTwoWorkout;
	List<Daily_Workout> dayThreeWorkout;
	List<Daily_Workout> dayFourWorkout;
	List<Daily_Workout> dayFiveWorkout;
	List<Daily_Workout> daySixWorkout;
	List<Client_DailyWorkout> oldClintDailyWorkouts;

	List<Day> allDays;
	List<Stratgey> allStratgies;
	List<Workout> musleWorkouts;
	List<Muscle> allMuscles;
	List<Set> allSets;
	private FileManger fileManager = null;

	int selectedDayId;
	int selectedMusleId;
	int selectedSetId;
	int selectedWorkoutId;
	String selectedStratgey;
	boolean isAddWorkout = false;

	public WorkoutBean() {

	}

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	public String initWorkout() {
		if (clientBean.getClient() == null || clientBean.getClient().getStrategyId() == null)
		{
			Constants.showMessage("Please Select Strategy First before Going to Workout Page,if already selected press save before going to workout page", true);
			return null;
		}
		dayOneWorkout = new ArrayList<>();
		dayTwoWorkout = new ArrayList<>();
		dayThreeWorkout = new ArrayList<>();
		dayFourWorkout = new ArrayList<>();
		dayFiveWorkout = new ArrayList<>();
		daySixWorkout = new ArrayList<>();

		fillClientDailyWorkouts();
		selectedStratgey = clientBean.getClient().getStrategyId().getValue();
		// initialize all static lists
		allDays = dayService.findAllDays();
		allMuscles = muscleService.findAllMuscles();
		allStratgies = stratgeyService.findAllStratgeys();
		musleWorkouts=new ArrayList<>();
		allSets = setService.findAllSets();
		selectedDayId = 0;
		selectedSetId = 0;
		selectedWorkoutId = 0;
		selectedMusleId = 0;
		return "workout";
	}

	public void getWorkoutsByMusleId() {
		if (selectedMusleId != 0) {
			musleWorkouts = workoutService.findAllWorkoutsByMuscleId(selectedMusleId);
		}
	}

	public void getStratgeyValue() {

		int setValue = setService.findSetById(selectedSetId).getNumber();
		String stratgeyValue = stratgeyService.findStratgeyById(clientBean.getSelectedStrategyId()).getValue();
		String startigiesTemp[] = stratgeyValue.split("\\/");

		selectedStratgey = "";
		for (int i = 0; i < setValue; i++) {
			selectedStratgey = selectedStratgey + startigiesTemp[i] + "/";
		}

		selectedStratgey = selectedStratgey.substring(0, selectedStratgey.length() - 1);
	}

	public String getMuscleByMuscleId(int muscleId) {

		return muscleService.findMuscleById(muscleId).getName();
	}

	public void removeDailyWorkout(Daily_Workout dailyWorkout, int day) {

		switch (day) {
		case 1:
			dayOneWorkout.remove(dailyWorkout);
			break;
		case 2:
			dayTwoWorkout.remove(dailyWorkout);
			break;
		case 3:
			dayThreeWorkout.remove(dailyWorkout);
			break;
		case 4:
			dayFourWorkout.remove(dailyWorkout);
			break;
		case 5:
			dayFiveWorkout.remove(dailyWorkout);
			break;
		case 6:
			daySixWorkout.remove(dailyWorkout);
			break;

		}
		isAddWorkout = true;

	}

	public void addDailyWorkout() {

		Daily_Workout selectedDailyWorkout = new Daily_Workout();

		selectedDailyWorkout.setDay(dayService.findDayById(selectedDayId));
		selectedDailyWorkout.setSet(setService.findSetById(selectedSetId));
		selectedDailyWorkout.setWorkout(workoutService.findWorkoutById(selectedWorkoutId));
		selectedDailyWorkout.setWorkoutStrategy(selectedStratgey);

		switch (selectedDayId) {
		case 1:
			dayOneWorkout.add(selectedDailyWorkout);
			break;
		case 2:
			dayTwoWorkout.add(selectedDailyWorkout);
			break;
		case 3:
			dayThreeWorkout.add(selectedDailyWorkout);
			break;
		case 4:
			dayFourWorkout.add(selectedDailyWorkout);
			break;
		case 5:
			dayFiveWorkout.add(selectedDailyWorkout);
			break;
		case 6:
			daySixWorkout.add(selectedDailyWorkout);
			break;

		}
		// reset selected values
		isAddWorkout = true;
		selectedSetId = 0;
		selectedStratgey = stratgeyService.findStratgeyById(clientBean.getSelectedStrategyId()).getValue();
	}

	public void saveDailyWorkouts() {
		try {
			List<Daily_Workout> daily_WorkoutList = new ArrayList<>();
			if (dayOneWorkout != null && dayOneWorkout.size() > 0) {
				daily_WorkoutList.addAll(dayOneWorkout);
			}
			if (dayTwoWorkout != null && dayTwoWorkout.size() > 0) {
				daily_WorkoutList.addAll(dayTwoWorkout);
			}
			if (dayThreeWorkout != null && dayThreeWorkout.size() > 0) {
				daily_WorkoutList.addAll(dayThreeWorkout);
			}
			if (dayFourWorkout != null && dayFourWorkout.size() > 0) {
				daily_WorkoutList.addAll(dayFourWorkout);
			}
			if (dayFiveWorkout != null && dayFiveWorkout.size() > 0) {
				daily_WorkoutList.addAll(dayFiveWorkout);
			}
			if (daySixWorkout != null && daySixWorkout.size() > 0) {
				daily_WorkoutList.addAll(daySixWorkout);
			}
			addClientDailyWorkout(daily_WorkoutList);
			generateReport();
		} catch (Exception e) {
			System.out.println("Error In Save daily workouts: "+e);
		}

	}

	public void addClientDailyWorkout(List<Daily_Workout> daily_WorkoutList) {

		deleteOldWorkouts();

		for (Daily_Workout daily_Workout : daily_WorkoutList) {

			dailyWorkoutService.save(daily_Workout);

			Client_DailyWorkout client_DailyWorkout = new Client_DailyWorkout();
			client_DailyWorkout.setDaily_Workout(daily_Workout);
			client_DailyWorkout.setClient(clientBean.getClient());
			clientDailyWorkoutService.save(client_DailyWorkout);
		}
		fillClientDailyWorkouts();
		isAddWorkout = false;
		Constants.showMessage("Workout Added Successfully", false);

	}

	public void deleteOldWorkouts() {

		if (oldClintDailyWorkouts != null && oldClintDailyWorkouts.size() > 0) {
			Iterator iter = oldClintDailyWorkouts.iterator();
			while (iter.hasNext()) {
				Client_DailyWorkout client_DailyWorkout = (Client_DailyWorkout) iter.next();
				clientDailyWorkoutService.delete(client_DailyWorkout);
				dailyWorkoutService.delete(client_DailyWorkout.getDaily_Workout());

				iter.remove();
			}
		}
	}

	public void fillClientDailyWorkouts() {

		oldClintDailyWorkouts = clientDailyWorkoutService
				.findClientDailyWorkoutByClientId(clientBean.getClient().getClientId());

		dayOneWorkout = new ArrayList<>();
		dayTwoWorkout = new ArrayList<>();
		dayThreeWorkout = new ArrayList<>();
		dayFourWorkout = new ArrayList<>();
		dayFiveWorkout = new ArrayList<>();
		daySixWorkout = new ArrayList<>();

		if (oldClintDailyWorkouts != null && oldClintDailyWorkouts.size() > 0) {
			for (Client_DailyWorkout client_DailyWorkout : oldClintDailyWorkouts) {
				Daily_Workout dailyWorkout = client_DailyWorkout.getDaily_Workout();
				switch (dailyWorkout.getDay().getNumber()) {

				case 1:
					dayOneWorkout.add(dailyWorkout);
					break;
				case 2:
					dayTwoWorkout.add(dailyWorkout);
					break;
				case 3:
					dayThreeWorkout.add(dailyWorkout);
					break;
				case 4:
					dayFourWorkout.add(dailyWorkout);
					break;
				case 5:
					dayFiveWorkout.add(dailyWorkout);
					break;
				case 6:
					daySixWorkout.add(dailyWorkout);
					break;

				}
			}
		}

	}

	public boolean generateReport() throws Exception {
		String[] generatedPDFName = null;
		try {

			if (clientBean.getClient() == null) {
				Constants.showMessage("Client Null in report", true);
				return false;
			}
			JasperParam jasperObj = new JasperParam();

			jasperObj.setClient(clientBean.getClient());
			jasperObj.setJasperXmlFolder(folderPathService.findFolderPathById(2));
			jasperObj.setJasperReportFolder(folderPathService.findFolderPathById(3));
			generatedPDFName = jasperIntegration.generateReport(jasperObj, true);
			downloadReport(generatedPDFName[0], generatedPDFName[1]);
			System.out.println("Done Download");
			int result = fileManager.deleteFileByPath(generatedPDFName[1]);
			if (result == 0) {
				System.out.println("Done Deleteing File");
				return true;
			} else {
				Constants.showMessage("File Not exists To Delete", true);
				return false;
			}

		} catch (Exception e) {
			throw e;
		}

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
			throw ex;
		}
		return result;
	}

	public List<Daily_Workout> getDayOneWorkout() {
		if (!isAddWorkout) {
			fillClientDailyWorkouts();
		}
		return dayOneWorkout;
	}

	public void setDayOneWorkout(List<Daily_Workout> dayOneWorkout) {
		this.dayOneWorkout = dayOneWorkout;
	}

	public List<Daily_Workout> getDayTwoWorkout() {
		return dayTwoWorkout;
	}

	public void setDayTwoWorkout(List<Daily_Workout> dayTwoWorkout) {
		this.dayTwoWorkout = dayTwoWorkout;
	}

	public List<Daily_Workout> getDayThreeWorkout() {
		return dayThreeWorkout;
	}

	public void setDayThreeWorkout(List<Daily_Workout> dayThreeWorkout) {
		this.dayThreeWorkout = dayThreeWorkout;
	}

	public List<Daily_Workout> getDayFourWorkout() {
		return dayFourWorkout;
	}

	public void setDayFourWorkout(List<Daily_Workout> dayFourWorkout) {
		this.dayFourWorkout = dayFourWorkout;
	}

	public List<Daily_Workout> getDayFiveWorkout() {
		return dayFiveWorkout;
	}

	public void setDayFiveWorkout(List<Daily_Workout> dayFiveWorkout) {
		this.dayFiveWorkout = dayFiveWorkout;
	}

	public List<Daily_Workout> getDaySixWorkout() {
		return daySixWorkout;
	}

	public void setDaySixWorkout(List<Daily_Workout> daySixWorkout) {
		this.daySixWorkout = daySixWorkout;
	}

	public DayService getDayService() {
		return dayService;
	}

	public void setDayService(DayService dayService) {
		this.dayService = dayService;
	}

	public StratgeyService getStratgeyService() {
		return stratgeyService;
	}

	public void setStratgeyService(StratgeyService stratgeyService) {
		this.stratgeyService = stratgeyService;
	}

	public WorkoutService getWorkoutService() {
		return workoutService;
	}

	public void setWorkoutService(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	public MuscleService getMuscleService() {
		return muscleService;
	}

	public void setMuscleService(MuscleService muscleService) {
		this.muscleService = muscleService;
	}

	public List<Day> getAllDays() {
		return allDays;
	}

	public void setAllDays(List<Day> allDays) {
		this.allDays = allDays;
	}

	public List<Stratgey> getAllStratgies() {
		return allStratgies;
	}

	public void setAllStratgies(List<Stratgey> allStratgies) {
		this.allStratgies = allStratgies;
	}

	public List<Muscle> getAllMuscles() {
		return allMuscles;
	}

	public void setAllMuscles(List<Muscle> allMuscles) {
		this.allMuscles = allMuscles;
	}

	public int getSelectedDayId() {
		return selectedDayId;
	}

	public void setSelectedDayId(int selectedDayId) {
		this.selectedDayId = selectedDayId;
	}

	public int getSelectedMusleId() {
		return selectedMusleId;
	}

	public void setSelectedMusleId(int selectedMusleId) {
		this.selectedMusleId = selectedMusleId;
	}

	public int getSelectedSetId() {
		return selectedSetId;
	}

	public void setSelectedSetId(int selectedSetId) {
		this.selectedSetId = selectedSetId;
	}

	public int getSelectedWorkoutId() {
		return selectedWorkoutId;
	}

	public void setSelectedWorkoutId(int selectedWorkoutId) {
		this.selectedWorkoutId = selectedWorkoutId;
	}

	public SetService getSetService() {
		return setService;
	}

	public void setSetService(SetService setService) {
		this.setService = setService;
	}

	public List<Set> getAllSets() {
		allSets = setService.findAllSets();
		return allSets;
	}

	public void setAllSets(List<Set> allSets) {
		this.allSets = allSets;
	}

	public List<Workout> getMusleWorkouts() {
		return musleWorkouts;
	}

	public void setMusleWorkouts(List<Workout> musleWorkouts) {
		this.musleWorkouts = musleWorkouts;
	}

	public ClientBean getClientBean() {
		return clientBean;
	}

	public void setClientBean(ClientBean clientBean) {
		this.clientBean = clientBean;
	}

	public Daily_WorkoutService getDailyWorkoutService() {
		return dailyWorkoutService;
	}

	public void setDailyWorkoutService(Daily_WorkoutService dailyWorkoutService) {
		this.dailyWorkoutService = dailyWorkoutService;
	}

	public Client_DailyWorkoutService getClientDailyWorkoutService() {
		return clientDailyWorkoutService;
	}

	public void setClientDailyWorkoutService(Client_DailyWorkoutService clientDailyWorkoutService) {
		this.clientDailyWorkoutService = clientDailyWorkoutService;
	}

	public String getSelectedStratgey() {
		return selectedStratgey;
	}

	public void setSelectedStratgey(String selectedStratgey) {
		this.selectedStratgey = selectedStratgey;
	}

	public FolderPathService getFolderPathService() {
		return folderPathService;
	}

	public void setFolderPathService(FolderPathService folderPathService) {
		this.folderPathService = folderPathService;
	}

	public JasperIntegration getJasperIntegration() {
		return jasperIntegration;
	}

	public void setJasperIntegration(JasperIntegration jasperIntegration) {
		this.jasperIntegration = jasperIntegration;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public List<Client_DailyWorkout> getOldClintDailyWorkouts() {
		return oldClintDailyWorkouts;
	}

	public void setOldClintDailyWorkouts(List<Client_DailyWorkout> oldClintDailyWorkouts) {
		this.oldClintDailyWorkouts = oldClintDailyWorkouts;
	}

	public FileManger getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManger fileManager) {
		this.fileManager = fileManager;
	}

	public String goBackToClientInfo() {
		return clientBean.goToEditClient();
	}

}
