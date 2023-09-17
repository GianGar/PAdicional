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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="PersonalHabilitado")
@Table(name="pa_personalhab")
public class PersonalHabilitado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	@Column(name="idEfectivo")
	int idEfectivo;
	@Column(name="arma")
	boolean arma;
	@Column(name="traje")
	boolean traje;
	@Column(name="uniforme")
	boolean uniforme;
	@Column(name="civilFormal")
	boolean civilFormal;
	@Column(name="activo")
	boolean activo;
	@Column(name="foto")
	String foto;
	@Transient
	Integer horasProximoMes;
	@Transient
	Integer serviciosProximoMes;

	@OneToOne(optional=false)
	@JoinColumn(name="idEfectivo",referencedColumnName="id", insertable = false, updatable = false)
	private Policia policia;
	
	@Column(name="id_codigo_gestion")
	private Integer id_codigo_gestion;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="id_codigo_gestion",referencedColumnName="id", insertable = false, updatable = false)
	private CodigoGestion codigoGestion;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="ph")
	private List<Suspension> suspensiones;

	
	
	@Override
	public String toString() {
		return "PersonalHabilitado [id=" + id + ", idEfectivo=" + idEfectivo + ", arma=" + arma + ", traje=" + traje
				+ ", uniforme=" + uniforme + ", civilFormal=" + civilFormal + ", activo=" + activo + ", foto=" + foto
				+ ", horasProximoMes=" + horasProximoMes + ", serviciosProximoMes=" + serviciosProximoMes + ", policia="
				+ policia + ", id_codigo_gestion=" + id_codigo_gestion + ", codigoGestion=" + codigoGestion
				+ ", suspensiones=" + suspensiones + "]";
	}

	public Integer getHorasProximoMes() {
		return horasProximoMes;
	}

	public void setHorasProximoMes(Integer horasProximoMes) {
		this.horasProximoMes = horasProximoMes;
	}

	public Integer getServiciosProximoMes() {
		return serviciosProximoMes;
	}

	public void setServiciosProximoMes(Integer serviciosProximoMes) {
		this.serviciosProximoMes = serviciosProximoMes;
	}










	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}



	public int getIdEfectivo() {
		return idEfectivo;
	}

	public void setIdEfectivo(int idEfectivo) {
		this.idEfectivo = idEfectivo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Policia getPolicia() {
		return policia;
	}

	public void setPolicia(Policia policia) {
		this.policia = policia;
	}

	public PersonalHabilitado() {}
	
	public PersonalHabilitado(int id, boolean arma, boolean traje, boolean uniforme, boolean civilFormal,
			boolean activo) {
		super();
		this.id = id;
		this.arma = arma;
		this.traje = traje;
		this.uniforme = uniforme;
		this.civilFormal = civilFormal;
		this.activo = activo;
	}

	public boolean isArma() {
		return arma;
	}
	public void setArma(boolean arma) {
		this.arma = arma;
	}
	public boolean isTraje() {
		return traje;
	}
	public void setTraje(boolean traje) {
		this.traje = traje;
	}
	public boolean isUniforme() {
		return uniforme;
	}
	public void setUniforme(boolean uniforme) {
		this.uniforme = uniforme;
	}
	public boolean isCivilFormal() {
		return civilFormal;
	}
	public void setCivilFormal(boolean civilFormal) {
		this.civilFormal = civilFormal;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}





	public Integer getId_codigo_gestion() {
		return id_codigo_gestion;
	}





	public void setId_codigo_gestion(Integer id_codigo_gestion) {
		this.id_codigo_gestion = id_codigo_gestion;
	}





	public CodigoGestion getCodigoGestion() {
		return codigoGestion;
	}





	public void setCodigoGestion(CodigoGestion codigoGestion) {
		this.codigoGestion = codigoGestion;
	}

	public List<Suspension> getSuspensiones() {
		return suspensiones;
	}

	public void setSuspensiones(List<Suspension> suspensiones) {
		this.suspensiones = suspensiones;
	}
	
	
	

	
	
	

}
