package com.psc.padicional.componentes;

public class Dia {
	
	String nombre;
	String fecha;
	boolean servicios;

	public Dia(String nombre, String fecha, boolean servicios) {
		super();
		this.nombre = nombre;
		this.fecha=fecha;
		this.servicios=servicios;
	}
	
	

	public boolean isServicios() {
		return servicios;
	}



	public void setServicios(boolean servicios) {
		this.servicios = servicios;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
