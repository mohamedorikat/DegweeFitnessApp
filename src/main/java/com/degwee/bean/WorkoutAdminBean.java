package com.degwee.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.degwee.model.Muscle;
import com.degwee.model.Stratgey;
import com.degwee.model.Workout;
import com.degwee.service.MuscleService;
import com.degwee.service.StratgeyService;
import com.degwee.service.WorkoutService;
import com.degwee.utils.Constants;

//@Component("clientBean")
//@Scope("session")
@ManagedBean(name = "workoutAdminBean")
@SessionScoped
public class WorkoutAdminBean {
	private Stratgey stratgey;
	private Workout workout;
	private Muscle muscle;
	private Muscle selectedMuscleForWokrout = null;
	private Integer selectedMuscleForWokroutId = null;

	@Autowired
	StratgeyService stratgeyService;
	@Autowired
	WorkoutService workoutService;
	@Autowired
	MuscleService muscleService;

	int mode = 0;
	List<Stratgey> allStratgies = null;
	List<Workout> allworkouts = null;
	List<Muscle> allMuscles = null;

	public Integer getSelectedMuscleForWokroutId() {
		return selectedMuscleForWokroutId;
	}

	public void setSelectedMuscleForWokroutId(Integer selectedMuscleForWokroutId) {
		this.selectedMuscleForWokroutId = selectedMuscleForWokroutId;
	}

	public Muscle getSelectedMuscleForWokrout() {
		return selectedMuscleForWokrout;
	}

	public void setSelectedMuscleForWokrout(Muscle selectedMuscleForWokrout) {
		this.selectedMuscleForWokrout = selectedMuscleForWokrout;
	}

	public Muscle getMuscle() {
		return muscle;
	}

	public void setMuscle(Muscle muscle) {
		this.muscle = muscle;
	}

	public List<Muscle> getAllMuscles() {
		allMuscles = muscleService.findAllMuscles();
		return allMuscles;
	}

	public void setAllMuscles(List<Muscle> allMuscles) {
		this.allMuscles = allMuscles;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public List<Workout> getAllworkouts() {
		allworkouts = workoutService.findAllWorkouts();
		return allworkouts;
	}

	public void setAllworkouts(List<Workout> allworkouts) {
		this.allworkouts = allworkouts;
	}

	public Stratgey getStratgey() {
		return stratgey;
	}

	public void setStratgey(Stratgey stratgey) {
		this.stratgey = stratgey;
	}

	public List<Stratgey> getAllStratgies() {
		allStratgies = stratgeyService.findAllStratgeys();
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
		if (stratgey.getValue() == null || stratgey.getValue().isEmpty()) {
			Constants.showMessage("Strategy Value required ", true);
			return "workoutAdmin";
		}
		try {
			String[] stragteyArr = stratgey.getValue().split("/");
			if (stragteyArr.length == 0) {
				Constants.showMessage("Please Remove Spaces And enter Stratgey value Slash based ", true);
				return "workoutAdmin";
			} else if (stragteyArr.length == 5) {
				stratgeyService.save(stratgey);
				Constants.showMessage("Stratgey Saved Successfully", false);
			} else {
				Constants.showMessage("Please enter Stratgey value Slash based & have 5 Numbers,ex:10/10/10/10/10", true);
				return "workoutAdmin";
			}
		} catch (Exception ex) {
			Constants.showMessage("Error While Save Stratgey,see Log error ex:" + ex.getMessage(), true);
			System.out.print(ex);
		}
		return "workoutAdmin";
	}

	public String deleteStratgey() {
		try {
			if (stratgey == null) {
				Constants.showMessage("Please Select stratgey before delete ", true);
				return "workoutAdmin";
			}
			stratgeyService.delete(stratgey);
			Constants.showMessage("Stratgey Deleted Successfully ", false);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			Constants.showMessage(
					"Cannot Delete Strategy because it is already used by clients Please Remove all Links with this strategy",
					false);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "workoutAdmin";

	}

	public void showCreateWorkoutDialog() {
		workout = new Workout();
		selectedMuscleForWokroutId = null;
		selectedMuscleForWokrout = null;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('addWorkoutDialog').show()");
	}

	public String createWorkout() {
		try {

			if (!checkWorkoutRequiredFields()) {
				selectedMuscleForWokrout = muscleService.findMuscleById(selectedMuscleForWokroutId);
				if (selectedMuscleForWokrout != null) {
					workout.setMuscle(selectedMuscleForWokrout);
					workoutService.save(workout);
					Constants.showMessage("Workout Saved Successfully", false);
				}
			}
		} catch (

		Exception ex) {
			Constants.showMessage("Error While Save workout,see Log error ex:" + ex.getMessage(), true);
			System.out.print(ex);
		}
		return "workoutAdmin";
	}

	private boolean checkWorkoutRequiredFields() {
		boolean requiredError = false;
		if (workout.getName() == null || workout.getName().isEmpty()) {
			Constants.showMessage("Workout Name Required", true);
			requiredError = true;
		}
		if (workout.getVideoLink() == null || workout.getVideoLink().isEmpty()) {
			Constants.showMessage("Workout Video Link Required", true);
			requiredError = true;
		}
		if (selectedMuscleForWokroutId == null) {
			Constants.showMessage("Workout Muscle Required", true);
			requiredError = true;
		}
		return requiredError;
	}

	public String deleteWorkout() {
		try {
			if (workout == null) {
				Constants.showMessage("Please Select workout before delete ", true);
				return "workoutAdmin";
			}
			workoutService.delete(workout);
			Constants.showMessage("Workout Deleted Successfully ", false);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			Constants.showMessage(
					"Cannot Delete Workout because it is already used by clients Please Remove all Links with this Workout",
					false);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "workoutAdmin";

	}

	public void showCreateMuscleDialog() {
		muscle = new Muscle();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('addMuscleDialog').show()");
	}

	public String createMuscle() {
		try {
			if (muscle.getName() == null || muscle.getName().isEmpty()) {
				Constants.showMessage("Muscle Name Required", true);
				return "workoutAdmin";
			}
			muscleService.save(muscle);
		} catch (Exception ex) {
			Constants.showMessage("Error While Save muscle,see Log error ex:" + ex.getMessage(), true);
			System.out.print(ex);
		}
		return "workoutAdmin";
	}

	public String deleteMuscle() {
		try {
			if (muscle == null) {
				Constants.showMessage("Please Select muscle before delete ", true);
				return "workoutAdmin";
			}
			muscleService.delete(muscle);
			Constants.showMessage("Muscle Deleted Successfully ", false);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			Constants.showMessage(
					"Cannot Delete Muscle because it is already used by clients Please Remove all Links with this Muscle",
					false);
		} catch (Exception e) {
			System.out.println(e);
		}
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
