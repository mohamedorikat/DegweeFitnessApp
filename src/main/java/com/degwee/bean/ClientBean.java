package com.degwee.bean;

import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.util.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.degwee.model.Client;
import com.degwee.model.ClientNutritionInfo;
import com.degwee.model.Stratgey;
import com.degwee.service.ClientNutritionInfoService;
import com.degwee.service.ClientService;
import com.degwee.service.StratgeyService;
import com.degwee.utils.Constants;

//@Component("clientBean")
//@Scope("session")
@ManagedBean(name = "clientBean")
@SessionScoped
public class ClientBean {
	private Client client;
	@Autowired
	ClientService clientService;
	@Autowired
	ClientNutritionInfoService clientNutritionInfoService;
	@Autowired
	StratgeyService strategyService;
	public boolean toCutFlag = false;
	int mode = 0;
	List<Client> allClients = null;
	boolean goToMealInfo = false;
	private Integer selectedStrategyId = null;
	private boolean enableMealWorkoutPages = false;

	public Integer getSelectedStrategyId() {
		return selectedStrategyId;
	}

	public void setSelectedStrategyId(Integer selectedStrategyId) {
		this.selectedStrategyId = selectedStrategyId;
	}

	public boolean isEnableMealWorkoutPages() {
		return enableMealWorkoutPages;
	}

	public void setEnableMealWorkoutPages(boolean enableMealWorkoutPages) {
		this.enableMealWorkoutPages = enableMealWorkoutPages;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isToCutFlag() {
		return toCutFlag;
	}

	public void setToCutFlag(boolean toCutFlag) {
		this.toCutFlag = toCutFlag;
	}

	public List<Client> getAllClients() {

		allClients = clientService.findAllClients();
		return allClients;
	}

	public void setAllClients(List<Client> allClients) {
		this.allClients = allClients;
	}

	public boolean isGoToMealInfo() {
		return goToMealInfo;
	}

	public void setGoToMealInfo(boolean goToMealInfo) {
		this.goToMealInfo = goToMealInfo;
	}

	public String goToCreateClient() {
		mode = Constants.createMode;
		String s = System.getProperty("user.dir");
		System.out.println("Path----------------------:" + s);
		String path = Paths.get(".").toAbsolutePath().normalize().toString();
		System.out.println("Path----------------------:" + path);
		client = new Client();
		client.setNutritionInfo(new ClientNutritionInfo());
		enableMealWorkoutPages = false;
		selectedStrategyId = null;
		return "clientInfo";
	}

	public String goToEditClient() {
		if (client == null) {
			FacesMessage facesMessage = new FacesMessage("Please Select Client From Client List To Edit");
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return null;
		}
		mode = Constants.editMode;
		if (client.getNutritionInfo().getGoal() == Constants.toCut)
			toCutFlag = true;
		else
			toCutFlag = false;
		enableMealWorkoutPages = false;
		if (client.getStrategyId() != null)
			selectedStrategyId = client.getStrategyId().getId();
		else
			selectedStrategyId = null;
		return "clientInfo";
	}

	public void calculateClientNutritionInfo() {
		if (client.getNutritionInfo() != null) {
			client.setNutritionInfo(clientNutritionInfoService.calculateNutritionInfo(client.getNutritionInfo()));
		}
	}

	public String saveClient() {

		if (client.getNutritionInfo() != null) {
			if (client.getNutritionInfo().getBMR() != null && client.getNutritionInfo().getTEE() != null
					&& client.getNutritionInfo().getTACN() != null) {
				if (selectedStrategyId != null)
					client.setStrategyId(strategyService.findStratgeyById(selectedStrategyId));
				else
					client.setStrategyId(null);

				if (client.getClientId() != null && mode == Constants.editMode) {
					clientService.update(client);
					FacesMessage facesMessage = new FacesMessage("Client Updated Successfully");
					facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
					FacesContext.getCurrentInstance().addMessage(null, facesMessage);
					goToMealInfo = true;
					enableMealWorkoutPages = true;
				} else {
					boolean alreadyExist = clientService.save(client);
					if (alreadyExist) {
						FacesMessage facesMessage = new FacesMessage("Client Already Exist with this Email");
						facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
						FacesContext.getCurrentInstance().addMessage(null, facesMessage);
						goToMealInfo = false;
						return null;
					}
					allClients = clientService.findAllClients();
					FacesMessage facesMessage = new FacesMessage("Client Saved Successfully");
					facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
					FacesContext.getCurrentInstance().addMessage(null, facesMessage);
					goToMealInfo = true;
					enableMealWorkoutPages = true;
				}
			} else {
				FacesMessage facesMessage = new FacesMessage("Calculate NutritionInfo First");
				facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				goToMealInfo = false;
			}
		}
		return null;

	}

	public void checkGoal() {
		resetNutritionValues();
		if (client.getNutritionInfo().getGoal() == Constants.toCut)
			toCutFlag = true;
		else
			toCutFlag = false;
	}

	public void resetNutritionValues() {
		client.getNutritionInfo().setBMR(null);
		client.getNutritionInfo().setTEE(null);
		client.getNutritionInfo().setTACN(null);
		client.getNutritionInfo().setLeanMass(null);
		client.getNutritionInfo().setCarbsToBulk(null);
		client.getNutritionInfo().setCarbsTocut(null);
		client.getNutritionInfo().setFatsToCut(null);
		client.getNutritionInfo().setFatsToBulk(null);
		client.getNutritionInfo().setProteinToBulk(null);
		client.getNutritionInfo().setProteinToCut(null);

	}

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

	public String goToWorkoutSelection() {
		if (client != null && client.getStrategyId() != null)
			return "workout";
		else {
			Constants.showMessage("Please Select Strategy First before Going to Workout Page", true);
			return null;
		}
	}

}
