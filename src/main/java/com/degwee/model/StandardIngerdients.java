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
@Table(name="standardingerdients")
public class StandardIngerdients {
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="FolderPathId",referencedColumnName="Id",nullable=true)
	private FolderPath folderPath;
	
	@Column(name="Name")
	private String name;
	@Column(name="ContainedProteins")
	@NotNull
	private Double containedProtiens;
	@Column(name="ContainedCarbs")
	@NotNull
	private Double containedCarbs;
	@Column(name="ContainedFats")
	@NotNull
	private Double containedFats;
	@Column(name="TotalCalories")
	@NotNull
	private Double totalCalories;
	@Column(name="ImageName")
	private String imageName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer Id) {
		this.id = Id;
	}
	public FolderPath getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(FolderPath folderPath) {
		this.folderPath = folderPath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getContainedProtiens() {
		return containedProtiens;
	}
	public void setContainedProtiens(Double containedProtiens) {
		this.containedProtiens = containedProtiens;
	}
	public Double getContainedCarbs() {
		return containedCarbs;
	}
	public void setContainedCarbs(Double containedCarbs) {
		this.containedCarbs = containedCarbs;
	}
	public Double getContainedFats() {
		return containedFats;
	}
	public void setContainedFats(Double containedFats) {
		this.containedFats = containedFats;
	}
	public Double getTotalCalories() {
		return totalCalories;
	}
	public void setTotalCalories(Double totalCalories) {
		this.totalCalories = totalCalories;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}
