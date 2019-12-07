package com.pc1crt.application.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Embeddable
@Table(name="cat")
public class Cat {
	private String name;
	@Id
	@NotNull
	@Column(name = "chip_no")
	private Integer chipNo;
	private MealPlan food;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate vaccinatedDate;
	private String temperment;
	private String litterType;
	private String otherInformatioin;
	@ManyToOne
	@JoinColumn(name = "owner")
	private Owner owner;
	
	public Cat() {}
	
	public Owner getOwner() {
		return owner;
	}


	public void setOwner(Owner owner) {
		this.owner = owner;
	}


	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getChipNo() {
		return chipNo;
	}
	public void setChipNo(Integer chipNo) {
		this.chipNo = chipNo;
	}
	public MealPlan getFood() {
		return food;
	}
	public void setFood(MealPlan food) {
		this.food = food;
	}
	public LocalDate getVaccinatedDate() {
		return vaccinatedDate;
	}
	public void setVaccinatedDate(LocalDate vaccinatedDate) {
		this.vaccinatedDate = vaccinatedDate;
	}
	public String getTemperment() {
		return temperment;
	}
	public void setTemperment(String temperment) {
		this.temperment = temperment;
	}
	public String getLitterType() {
		return litterType;
	}
	public void setLitterType(String litterType) {
		this.litterType = litterType;
	}
	public String getOtherInformatioin() {
		return otherInformatioin;
	}
	public void setOtherInformatioin(String otherInformatioin) {
		this.otherInformatioin = otherInformatioin;
	}


	@Override
	public String toString() {
		return "Cat [name=" + name + ", chipNo=" + chipNo + ", food=" + food + ", vaccinatedDate=" + vaccinatedDate
				+ ", temperment=" + temperment + ", litterType=" + litterType + ", otherInformatioin="
				+ otherInformatioin + ", owner=" + owner + "]";
	}
	
	

}
