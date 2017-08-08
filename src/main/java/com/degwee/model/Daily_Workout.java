package com.degwee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "daily_workout")
public class Daily_Workout {

	public Daily_Workout() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "workout_strategy")
	@NotNull
	private String workoutStrategy;
	@ManyToOne
	@JoinColumn(name = "day_id", referencedColumnName = "id", nullable = false)
	private Day day;
	@ManyToOne
	@JoinColumn(name = "set_id", referencedColumnName = "id", nullable = false)
	private Set set;
	@ManyToOne
	@JoinColumn(name = "workout_id", referencedColumnName = "id", nullable = false)
	private Workout workoutId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWorkoutStrategy() {
		return workoutStrategy;
	}
	public void setWorkoutStrategy(String workoutStrategy) {
		this.workoutStrategy = workoutStrategy;
	}
	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		this.day = day;
	}
	public Set getSet() {
		return set;
	}
	public void setSet(Set set) {
		this.set = set;
	}
	public Workout getWorkoutId() {
		return workoutId;
	}
	public void setWorkoutId(Workout workoutId) {
		this.workoutId = workoutId;
	}
	

}
