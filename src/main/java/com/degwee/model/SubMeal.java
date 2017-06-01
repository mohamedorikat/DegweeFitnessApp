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
@Table(name="submeal")
public class SubMeal {
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name="MealId",referencedColumnName="Id",nullable=false)
	private Meal meal;
	
	@ManyToOne
	@JoinColumn(name="StandardIngerdientId",referencedColumnName="Id",nullable=false)
	private StandardIngerdients standardIngerdients;
	
	@Column(name="IngredientPortion")
	@NotNull
	private Integer ingerdientPortion;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public StandardIngerdients getStandardIngerdients() {
		return standardIngerdients;
	}

	public void setStandardIngerdients(StandardIngerdients standardIngerdients) {
		this.standardIngerdients = standardIngerdients;
	}

	public Integer getIngerdientPortion() {
		return ingerdientPortion;
	}

	public void setIngerdientPortion(Integer ingerdientPortion) {
		this.ingerdientPortion = ingerdientPortion;
	}
	
	
}
