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

@Entity
@Table(name="pa_guardias")
public class Guardia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="idObjetivo")
	private int idObjetivo;
	@Column(name="hora_entrada")
	private String horaEntrada;
	@Column(name="hora_salida")
	private String horaSalida;
	@Transient
	private Integer duracion;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name="idObjetivo",referencedColumnName="id", insertable = false, updatable = false)
	private Objetivo objetivo;
	
	public Guardia() {}

	public Guardia(int id, int idObjetivo, String horaEntrada, String horaSalida) {
		super();
		this.id = id;
		this.idObjetivo = idObjetivo;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(int idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}
	
	
	
	

}
