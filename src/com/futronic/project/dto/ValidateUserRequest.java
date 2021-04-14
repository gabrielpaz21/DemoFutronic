package com.futronic.project.dto;

public class ValidateUserRequest {
	
	public ValidateUserRequest(String dni) {
		super();
		this.dni = dni;
	}

	private String dni;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
}
