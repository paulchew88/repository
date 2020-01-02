package com.pc1crt.application.model;

public enum RoomType {
Standard_Room("Standard room"),
Family_Room("Family Room");

	private final String string;
	private RoomType(String string) {
		this.string = string;
	}
}
