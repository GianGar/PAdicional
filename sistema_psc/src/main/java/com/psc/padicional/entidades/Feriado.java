package com.psc.padicional.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="feriados")
public class Feriado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="fecha")
	private String fecha;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="tipo")
	private String tipo;
	@Transient
	private String diaSemana;
	@Transient
	private String fechaFormat;
	
	public Feriado() {}
	public Feriado(int id, String fecha, String descripcion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getFechaFormat() {
		return fechaFormat;
	}
	public void setFechaFormat(String fechaFormat) {
		this.fechaFormat = fechaFormat;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	

}
