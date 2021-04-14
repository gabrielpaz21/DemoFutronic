package com.futronic.project.dto;

public class RegisterRequest {

	private String firstName;

	private String lastName;

	private String dni;

	private String templateRightThumbFinger;

	private String bitMapRightThumbFinger;

	private String templateRightIndexFinger;

	private String bitMapRightIndexFinger;

	public RegisterRequest(String firstName, String lastName, String dni) {

		this.firstName = firstName;

		this.lastName = lastName;

		this.dni = dni;

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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTemplateRightThumbFinger() {
		return templateRightThumbFinger;
	}

	public void setTemplateRightThumbFinger(String templateRightThumbFinger) {
		this.templateRightThumbFinger = templateRightThumbFinger;
	}

	public String getBitMapRightThumbFinger() {
		return bitMapRightThumbFinger;
	}

	public void setBitMapRightThumbFinger(String bitMapRightThumbFinger) {
		this.bitMapRightThumbFinger = bitMapRightThumbFinger;
	}

	public String getTemplateRightIndexFinger() {
		return templateRightIndexFinger;
	}

	public void setTemplateRightIndexFinger(String templateRightIndexFinger) {
		this.templateRightIndexFinger = templateRightIndexFinger;
	}

	public String getBitMapRightIndexFinger() {
		return bitMapRightIndexFinger;
	}

	public void setBitMapRightIndexFinger(String bitMapRightIndexFinger) {
		this.bitMapRightIndexFinger = bitMapRightIndexFinger;
	}

}
