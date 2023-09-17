package com.psc.padicional.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Policia")
@Table(name="leg_policias")
public class Policia {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	@Column(name="nro_agente")
	int nroAgente;
	@Column(name="idPersona")
	int idPersona;
	@Column(name="idJerarquia")
	int idJerarquia;
	@Column(name="fecha_ingreso")
	String fechaIngreso;
	@Column(name="idDestino")
	int idDestino;
	@Column(name="idSitRev")
	int idSitRev;
	@Column(name="idEscalafon")
	int idEscalafon;
	@Column(name="credencial")
	String credencial;
	
	@OneToOne(optional=false)
	@JoinColumn(name="idPersona",referencedColumnName="id", insertable = false, updatable = false)
	private Persona persona;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="idJerarquia",referencedColumnName="id", insertable = false, updatable = false)
	private Jerarquia jerarquia;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="idDestino",referencedColumnName="id", insertable = false, updatable = false)
	private Destino destino;
	

	



	@Override
	public String toString() {
		return "Policia [id=" + id + ", nroAgente=" + nroAgente + ", idPersona=" + idPersona + ", idJerarquia="
				+ idJerarquia + ", fechaIngreso=" + fechaIngreso + ", idDestino=" + idDestino + ", idSitRev=" + idSitRev
				+ ", idEscalafon=" + idEscalafon + ", credencial=" + credencial + ", persona=" + persona
				+ ", jerarquia=" + jerarquia + ", destino=" + destino + "]";
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Policia() {/**/}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNroAgente() {
		return nroAgente;
	}

	public void setNroAgente(int nroAgente) {
		this.nroAgente = nroAgente;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdJerarquia() {
		return idJerarquia;
	}

	public void setIdJerarquia(int idJerarquia) {
		this.idJerarquia = idJerarquia;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public int getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}

	public int getIdSitRev() {
		return idSitRev;
	}

	public void setIdSitRev(int idSitRev) {
		this.idSitRev = idSitRev;
	}

	public int getIdEscalafon() {
		return idEscalafon;
	}

	public void setIdEscalafon(int idEscalafon) {
		this.idEscalafon = idEscalafon;
	}

	public String getCredencial() {
		return credencial;
	}

	public void setCredencial(String credencial) {
		this.credencial = credencial;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Jerarquia getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(Jerarquia jerarquia) {
		this.jerarquia = jerarquia;
	}

	
	
	

}
