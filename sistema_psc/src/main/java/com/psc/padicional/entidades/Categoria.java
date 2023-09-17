package com.psc.padicional.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="Categoria")
@Table(name="pa_categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="valorHora")
	private float valorHora;
	@Transient
	private String valorHoraFormat;
	
	public Categoria(int id, String nombre, float valorHora) {
		this.id = id;
		this.nombre = nombre;
		this.valorHora = valorHora;
	}
	
	public Categoria() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getValorHora() {
		return valorHora;
	}

	public void setValorHora(float valorHora) {
		this.valorHora = valorHora;
	}

	public String getValorHoraFormat() {
		return valorHoraFormat;
	}

	public void setValorHoraFormat(String valorHoraFormat) {
		this.valorHoraFormat = valorHoraFormat;
	}
	
	
	
	

}
