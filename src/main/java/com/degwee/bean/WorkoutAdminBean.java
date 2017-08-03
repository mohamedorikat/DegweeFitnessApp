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

import org.primefaces.context.RequestContext;
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
@ManagedBean(name = "workoutAdminBean")
@SessionScoped
public class WorkoutAdminBean {
	private Stratgey stratgey;
	@Autowired
	StratgeyService stratgeyService;

	int mode = 0;
	List<Stratgey> allStratgies = null;

	public Stratgey getStratgey() {
		allStratgies = stratgeyService.findAllStratgeys();
		return stratgey;
	}

	public void setStratgey(Stratgey stratgey) {
		this.stratgey = stratgey;
	}

	public List<Stratgey> getAllStratgies() {
		return allStratgies;
	}

	public void setAllStratgies(List<Stratgey> allStratgies) {
		this.allStratgies = allStratgies;
	}

	public void showCreateStratgeyDialog() {
		stratgey = new Stratgey();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('addStratgeyDialog').show()");
	}

	public String createStratgey() {
		if (stratgey.getValue().isEmpty()) {
			Constants.showMessage("Please Remove Spaces And enter Stratgey value Slash based ", true);
			return "workoutAdmin";
		}
		try {
			String[] stragteyArr = stratgey.getValue().split("/");
			if (stragteyArr.length == 0) {
				Constants.showMessage("Please Remove Spaces And enter Stratgey value Slash based ", true);
				return "workoutAdmin";
			} else if (stragteyArr.length > 5) {
				Constants.showMessage("Pleas enter Stratgey value Slash based With Max 5 digits", true);
				return "workoutAdmin";
			} else {
				stratgeyService.save(stratgey);
				Constants.showMessage("Stratgey Saved Successfully", false);
			}
			allStratgies=stratgeyService.findAllStratgeys();
		} catch (Exception ex) {
			Constants.showMessage("Error While Save Stratgey,see Log error ex:" + ex.getMessage(), true);
			System.out.print(ex);
		}
		return "workoutAdmin";
	}

	public String deleteStratgey() {
		if(stratgey == null)
		{
			Constants.showMessage("Please Select stratgey before delete ", true);
			return "workoutAdmin";
		}
		stratgeyService.delete(stratgey);
		allStratgies=stratgeyService.findAllStratgeys();
		Constants.showMessage("Stratgey Deleted Successfully ", false);
		return "workoutAdmin";

	}

	@PostConstruct
	private void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

}
