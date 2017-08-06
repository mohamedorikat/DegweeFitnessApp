/**
 * 
 */
package com.degwee.model;



/**
 * @author orikat
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author orikat
 *
 */
@Entity
@Table(name="client")
public class Client {
	public Client() {
	}
	@Id
	@Column(name="ClientId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ClientId;
	
	@ManyToOne
	@JoinColumn(name="NutritionId",referencedColumnName="Id",nullable=false)
	private ClientNutritionInfo nutritionInfo;
	
	@ManyToOne
	@JoinColumn(name="strategy_id",referencedColumnName="id",nullable=true)
	private Stratgey strategyId;
	
	@Column(name="PhoneNumber")
	private String phoneNumber;
	@Column(name="Email")
	private String email;
	@Column(name="FirstName")
	private String firstName;
	@Column(name="LastName")
	private String lastName;
	@Column(name="Age")
	private Integer age;
	@Column(name="GENDER",length=1)
	private String gender;
	public Integer getClientId() {
		return ClientId;
	}
	public void setClientId(Integer clientId) {
		this.ClientId = clientId;
	}
	public ClientNutritionInfo getNutritionInfo() {
		return nutritionInfo;
	}
	public void setNutritionInfo(ClientNutritionInfo nutritionInfo) {
		this.nutritionInfo = nutritionInfo;
	}
	public Stratgey getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(Stratgey strategyId) {
		this.strategyId = strategyId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}


}