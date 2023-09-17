package com.psc.padicional.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="Suspension")
@Table(name="pa_suspensiones")
public class Suspension {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="idPH")
	private int idPH;
	@Column(name="detalle")
	private String detalle;
	@Column(name="fecha_inicio")
	private String fecha_inicio;
	@Column(name="fecha_fin")
	private String fecha_fin;
	@ManyToOne(optional=false)
	@JoinColumn(name="idPH",referencedColumnName="id", insertable = false, updatable = false)
	private PersonalHabilitado ph;
	@Transient
	String fechaInicioFormat;
	@Transient
	String fechaFinFormat;
	
	public Suspension() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPH() {
		return idPH;
	}

	public void setIdPH(int idPH) {
		this.idPH = idPH;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public PersonalHabilitado getPh() {
		return ph;
	}

	public void setPh(PersonalHabilitado ph) {
		this.ph = ph;
	}

	public String getFechaInicioFormat() {
		return fechaInicioFormat;
	}

	public void setFechaInicioFormat(String fechaInicioFormat) {
		this.fechaInicioFormat = fechaInicioFormat;
	}

	public String getFechaFinFormat() {
		return fechaFinFormat;
	}

	public void setFechaFinFormat(String fechaFinFormat) {
		this.fechaFinFormat = fechaFinFormat;
	}
	
	


	
	
	
	
	

}
