package com.pc1crt.application.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.boot.model.relational.Namespace.Name;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Embeddable
@Table(name = "cat")
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
	private String otherInformation;
	public Cat() {
	}

	public Cat(String name, @NotNull Integer chipNo, MealPlan food, LocalDate vaccinatedDate, String temperment,
			String litterType, String otherInformation) {
		this.name = name;
		this.chipNo = chipNo;
		this.food = food;
		this.vaccinatedDate = vaccinatedDate;
		this.temperment = temperment;
		this.litterType = litterType;
		this.otherInformation = otherInformation;
		
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

	public String getOtherInformation() {
		return otherInformation;
	}

	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cat [name=");
		builder.append(name);
		builder.append(", chipNo=");
		builder.append(chipNo);
		builder.append(", food=");
		builder.append(food);
		builder.append(", vaccinatedDate=");
		builder.append(vaccinatedDate);
		builder.append(", temperment=");
		builder.append(temperment);
		builder.append(", litterType=");
		builder.append(litterType);
		builder.append(", otherInformation=");
		builder.append(otherInformation);
		builder.append("]");
		return builder.toString();
	}



}