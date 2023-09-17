package com.psc.padicional.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="Objetivo")
@Table(name="pa_objetivos")
public class Objetivo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="idEnte")
	private int idEnte;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="consigna")
	private String consigna;
	@Column(name="estado")
	private String estado;
	@Column(name="arma")
	private boolean arma;
	@Column(name="uniforme")
	private boolean uniforme;
	@Column(name="traje")
	private boolean traje;
	@Column(name="civilFormal")
	private boolean civilFormal;
	@Column(name="lunes")
	private boolean lunes;
	@Column(name="martes")
	private boolean martes;
	@Column(name="miercoles")
	private boolean miercoles;
	@Column(name="jueves")
	private boolean jueves;
	@Column(name="viernes")
	private boolean viernes;
	@Column(name="sabado")
	private boolean sabado;
	@Column(name="domingo")
	private boolean domingo;
	@Column(name="feriados")
	private boolean feriados;
	@Column(name="efectivos")
	private int efectivos;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="idEnte",referencedColumnName="id", insertable = false, updatable = false)
	private Ente ente;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="objetivo" )
	private List<Guardia> guardias;

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public List<Guardia> getGuardias() {
		return guardias;
	}

	public void setGuardias(List<Guardia> guardias) {
		this.guardias = guardias;
	}

	public Objetivo() {}
	
	public Objetivo(int id, int idEnte, String descripcion, String consigna, String estado, boolean arma,
			boolean uniforme, boolean traje, boolean civilFormal, boolean lunes, boolean martes, boolean miercoles,
			boolean jueves, boolean viernes, boolean sabado, boolean domingo, boolean feriados) {
		this.id = id;
		this.idEnte = idEnte;
		this.descripcion = descripcion;
		this.consigna = consigna;
		this.estado = estado;
		this.arma = arma;
		this.uniforme = uniforme;
		this.traje = traje;
		this.civilFormal = civilFormal;
		this.lunes = lunes;
		this.martes = martes;
		this.miercoles = miercoles;
		this.jueves = jueves;
		this.viernes = viernes;
		this.sabado = sabado;
		this.domingo = domingo;
		this.feriados = feriados;
	}

	public int getEfectivos() {
		return efectivos;
	}

	public void setEfectivos(int efectivos) {
		this.efectivos = efectivos;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdEnte() {
		return idEnte;
	}
	public void setIdEnte(int idEnte) {
		this.idEnte = idEnte;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getConsigna() {
		return consigna;
	}
	public void setConsigna(String consigna) {
		this.consigna = consigna;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean isArma() {
		return arma;
	}
	public void setArma(boolean arma) {
		this.arma = arma;
	}
	public boolean isUniforme() {
		return uniforme;
	}
	public void setUniforme(boolean uniforme) {
		this.uniforme = uniforme;
	}
	public boolean isTraje() {
		return traje;
	}
	public void setTraje(boolean traje) {
		this.traje = traje;
	}
	public boolean isCivilFormal() {
		return civilFormal;
	}
	public void setCivilFormal(boolean civilFormal) {
		this.civilFormal = civilFormal;
	}
	public boolean isLunes() {
		return lunes;
	}
	public void setLunes(boolean lunes) {
		this.lunes = lunes;
	}
	public boolean isMartes() {
		return martes;
	}
	public void setMartes(boolean martes) {
		this.martes = martes;
	}
	public boolean isMiercoles() {
		return miercoles;
	}
	public void setMiercoles(boolean miercoles) {
		this.miercoles = miercoles;
	}
	public boolean isJueves() {
		return jueves;
	}
	public void setJueves(boolean jueves) {
		this.jueves = jueves;
	}
	public boolean isViernes() {
		return viernes;
	}
	public void setViernes(boolean viernes) {
		this.viernes = viernes;
	}
	public boolean isSabado() {
		return sabado;
	}
	public void setSabado(boolean sabado) {
		this.sabado = sabado;
	}
	public boolean isDomingo() {
		return domingo;
	}
	public void setDomingo(boolean domingo) {
		this.domingo = domingo;
	}
	public boolean isFeriados() {
		return feriados;
	}
	public void setFeriados(boolean feriados) {
		this.feriados = feriados;
	}
}