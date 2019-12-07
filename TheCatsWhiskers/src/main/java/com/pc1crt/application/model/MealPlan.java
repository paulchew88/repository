package com.pc1crt.application.model;

public enum MealPlan {
	Luxury("Luxury"),
	Standard("Standard"),
	Use_Own_Food("use own food");
	
	private final String string;
	private MealPlan(String string) {
		this.string = string;
	}
}
