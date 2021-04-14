package com.futronic.project.dto;

public class ErrorResponse {
	
	private String estado;
	private String fecha;
	private String mensajeProgramador;
	private String mensajeCliente;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getMensajeProgramador() {
		return mensajeProgramador;
	}
	public void setMensajeProgramador(String mensajeProgramador) {
		this.mensajeProgramador = mensajeProgramador;
	}
	public String getMensajeCliente() {
		return mensajeCliente;
	}
	public void setMensajeCliente(String mensajeCliente) {
		this.mensajeCliente = mensajeCliente;
	}
	
}
