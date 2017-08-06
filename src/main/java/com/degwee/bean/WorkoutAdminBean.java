package com.degwee.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.degwee.model.Muscle;
import com.degwee.model.Stratgey;
import com.degwee.model.Workout;
import com.degwee.model.Workout_Muscle;
import com.degwee.service.MuscleService;
import com.degwee.service.StratgeyService;
import com.degwee.service.WorkoutService;
import com.degwee.service.Workout_MuscleService;
import com.degwee.utils.Constants;

//@Component("clientBean")
//@Scope("session")
@ManagedBean(name = "workoutAdminBean")
@SessionScoped
public class WorkoutAdminBean {
	private Stratgey stratgey;
	private Workout workout;
	private Muscle muscle;
	private Workout_Muscle workout_Muscle;
	private Integer selectedWorkoutMuscleId;

	@Autowired
	StratgeyService stratgeyService;
	@Autowired
	WorkoutService workoutService;
	@Autowired
	MuscleService muscleService;
	@Autowired
	Workout_MuscleService workout_MuscleService;

	int mode = 0;
	List<Stratgey> allStratgies = null;
	List<Workout> allworkouts = null;
	List<Muscle> allMuscles = null;

	
	
	public Integer getSelectedWorkoutMuscleId() {
		return selectedWorkoutMuscleId;
	}

	public void setSelectedWorkoutMuscleId(Integer selectedWorkoutMuscleId) {
		this.selectedWorkoutMuscleId = selectedWorkoutMuscleId;
	}

	public Workout_Muscle getWorkout_Muscle() {
		return workout_Muscle;
	}

	public void setWorkout_Muscle(Workout_Muscle workout_Muscle) {
		this.workout_Muscle = workout_Muscle;
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
		} catch (Exception ex) {
			Constants.showMessage("Error While Save Stratgey,see Log error ex:" + ex.getMessage(), true);
			System.out.print(ex);
		}
		return "workoutAdmin";
	}

	public String deleteStratgey() {
		if (stratgey == null) {
			Constants.showMessage("Please Select stratgey before delete ", true);
			return "workoutAdmin";
		}
		stratgeyService.delete(stratgey);
		Constants.showMessage("Stratgey Deleted Successfully ", false);
		return "workoutAdmin";

	}

	public void showCreateWorkoutDialog() {
		selectedWorkoutMuscleId=null;
		workout = new Workout();
		workout_Muscle = new Workout_Muscle();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('addWorkoutDialog').show()");
	}

	public String createWorkout() {
		try {
			workoutService.save(workout);
			Muscle selectedMuscle=muscleService.findMuscleById(selectedWorkoutMuscleId);
			workout_Muscle.setMuscle(selectedMuscle);
			workout_Muscle.setWorkout(workout);
			workout_MuscleService.save(workout_Muscle);
		} catch (Exception ex) {
			Constants.showMessage("Error While Save workout,see Log error ex:" + ex.getMessage(), true);
			System.out.print(ex);
		}
		return "workoutAdmin";
	}

	public String deleteWorkout() {
		if (workout == null) {
			Constants.showMessage("Please Select workout before delete ", true);
			return "workoutAdmin";
		}	
		workout_MuscleService.deleteWorkoutMuscleByWorkout(workout);
		workoutService.delete(workout);
		Constants.showMessage("Workout Deleted Successfully ", false);
		return "workoutAdmin";

	}

	public void showCreateMuscleDialog() {
		muscle = new Muscle();
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('addMuscleDialog').show()");
	}

	public String createMuscle() {
		try {
			muscleService.save(muscle);
		} catch (Exception ex) {
			Constants.showMessage("Error While Save muscle,see Log error ex:" + ex.getMessage(), true);
			System.out.print(ex);
		}
		return "workoutAdmin";
	}

	public String deleteMuscle() {
		if (muscle == null) {
			Constants.showMessage("Please Select muscle before delete ", true);
			return "workoutAdmin";
		}
		muscleService.delete(muscle);
		Constants.showMessage("Muscle Deleted Successfully ", false);
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
