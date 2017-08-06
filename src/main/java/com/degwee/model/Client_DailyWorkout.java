package com.degwee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "client_dailyworkout")
public class Client_DailyWorkout {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "ClientId", nullable = false)
	private Client client;

	@ManyToOne
	@JoinColumn(name = "dailyWorkout_id", referencedColumnName = "id", nullable = false)
	private Daily_Workout daily_Workout;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Daily_Workout getDaily_Workout() {
		return daily_Workout;
	}

	public void setDaily_Workout(Daily_Workout daily_Workout) {
		this.daily_Workout = daily_Workout;
	}

	
	

}
