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
@Table(name="workout")
public class Workout {
	
	public Workout() {
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name")
	@NotNull
	private String name;
	
	@Column(name="videoLink")
	@NotNull
	private String videoLink;
	
	@ManyToOne
	@JoinColumn(name="muscleId",referencedColumnName="id",nullable=false)
	private Muscle muscle;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	public Muscle getMuscle() {
		return muscle;
	}
	public void setMuscle(Muscle muscle) {
		this.muscle = muscle;
	}
	
}
