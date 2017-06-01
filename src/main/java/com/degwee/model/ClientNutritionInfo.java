/**
 * 
 */
package com.degwee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author orikat
 *
 */
@Entity
@Table(name="client_nutrition_info")
public class ClientNutritionInfo {
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;
	
	
	@Column(name="Height")
	@NotNull
	private Integer height;
	@Column(name="Weight")
	@NotNull
	private Double weight;
	@Column(name="BodyFat")
	@NotNull
	private Integer bodyFat;
	@Column(name="Goal",length=1)
	@NotNull
	private Integer goal;
	@Column(name="ActivityRate")
	@NotNull
	private Float activityRate;
	@Column(name="LeanMass")
	@NotNull
	private Double leanMass;
	@Column(name="BMR")
	@NotNull
	private Double BMR;
	@Column(name="TEE")
	@NotNull
	private Double TEE;
	@Column(name="TACN")
	@NotNull
	private Double TACN;
	@Column(name="ProteinToCut")
	private Double proteinToCut;
	@Column(name="FatsToCut")
	private Double fatsToCut;
	@Column(name="CarbsToCut")
	private Double carbsTocut;
	@Column(name="ProteinToBulk")
	private Double proteinToBulk;
	@Column(name="FatsToBulk")
	private Double fatsToBulk;
	@Column(name="CarbsToBulk")
	private Double carbsToBulk;
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		this.Id = id;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public Integer getBodyFat() {
		return bodyFat;
	}
	public void setBodyFat(Integer bodyFat) {
		this.bodyFat = bodyFat;
	}
	public Integer getGoal() {
		return goal;
	}
	public void setGoal(Integer goal) {
		this.goal = goal;
	}
	public Float getActivityRate() {
		return activityRate;
	}
	public void setActivityRate(Float activityRate) {
		this.activityRate = activityRate;
	}
	public Double getLeanMass() {
		return leanMass;
	}
	public void setLeanMass(Double leanMass) {
		this.leanMass = leanMass;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getBMR() {
		return BMR;
	}
	public void setBMR(Double bMR) {
		BMR = bMR;
	}
	public Double getTEE() {
		return TEE;
	}
	public void setTEE(Double tEE) {
		TEE = tEE;
	}
	public Double getTACN() {
		return TACN;
	}
	public void setTACN(Double tACN) {
		TACN = tACN;
	}
	public Double getProteinToCut() {
		return proteinToCut;
	}
	public void setProteinToCut(Double proteinToCut) {
		this.proteinToCut = proteinToCut;
	}
	public Double getFatsToCut() {
		return fatsToCut;
	}
	public void setFatsToCut(Double fatsToCut) {
		this.fatsToCut = fatsToCut;
	}
	public Double getCarbsTocut() {
		return carbsTocut;
	}
	public void setCarbsTocut(Double carbsTocut) {
		this.carbsTocut = carbsTocut;
	}
	public Double getProteinToBulk() {
		return proteinToBulk;
	}
	public void setProteinToBulk(Double proteinToBulk) {
		this.proteinToBulk = proteinToBulk;
	}
	public Double getFatsToBulk() {
		return fatsToBulk;
	}
	public void setFatsToBulk(Double fatsToBulk) {
		this.fatsToBulk = fatsToBulk;
	}
	public Double getCarbsToBulk() {
		return carbsToBulk;
	}
	public void setCarbsToBulk(Double carbsToBulk) {
		this.carbsToBulk = carbsToBulk;
	}
		
	

}
