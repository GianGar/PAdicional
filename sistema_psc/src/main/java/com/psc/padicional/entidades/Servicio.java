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

import com.psc.padicional.componentes.Utilidades;
import com.querydsl.core.annotations.QueryEntity;

@QueryEntity
@Entity(name="Servicio")
@Table(name="pa_servicios")
public class Servicio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="mes")
	private Integer mes;
	@Column(name="anio")
	private Integer anio;
	@Column(name="idObjetivo")
	private int idObjetivo;
	@Column(name="fechaHoraEntrada")
	private String fechaHoraEntrada;
	@Column(name="fechaHoraSalida")
	private String fechaHoraSalida;
	@Column(name="importeHora")
	private double importeHora;
	@Column(name="oficio")
	private String oficio;
	@Column(name="efectivo")
	private Integer efectivo;
	@Column(name="jerarquia")
	private String jerarquia;
	@Column(name="destino")
	private String destino;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="idObjetivo",referencedColumnName="id", insertable = false, updatable = false)
	private Objetivo objetivo;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="efectivo",referencedColumnName="id", insertable = false, updatable = false)
	private Policia policia;
	
	@Transient
	private String horaEntrada;
	
	@Transient
	private String horaSalida;
	
	@Transient
	private String fecha;
	
	@Transient
	private String dia;
	
	@Transient
	private Integer numeroListado;
	
	public Servicio(int id, int mes, int anio, int idObjetivo, String fechaHoraEntrada, String fechaHoraSalida,
			double importeHora, String oficio, int efectivo, String jerarquia, String destino) {
		super();
		this.id = id;
		this.mes = mes;
		this.anio = anio;
		this.idObjetivo = idObjetivo;
		this.fechaHoraEntrada = fechaHoraEntrada;
		this.fechaHoraSalida = fechaHoraSalida;
		this.importeHora = importeHora;
		this.oficio = oficio;
		this.efectivo = efectivo;
		this.jerarquia = jerarquia;
		this.destino = destino;
	}
	
	public Servicio() {}

	public String getDia() {
		return Utilidades.diaSemana(getFechaHoraEntrada());
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getFecha() {
		return Utilidades.formatearFecha(getFechaHoraEntrada());
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraSalida() {
		return Utilidades.obtenerHora(getFechaHoraSalida());
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getHoraEntrada() {
		return Utilidades.obtenerHora(getFechaHoraEntrada());
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Policia getPolicia() {
		return policia;
	}

	public void setPolicia(Policia policia) {
		this.policia = policia;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public int getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(int idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public String getFechaHoraEntrada() {
		return fechaHoraEntrada;
	}

	public void setFechaHoraEntrada(String fechaHoraEntrada) {
		this.fechaHoraEntrada = fechaHoraEntrada;
	}

	public String getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public void setFechaHoraSalida(String fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}

	public double getImporteHora() {
		return importeHora;
	}

	public void setImporteHora(double importeHora) {
		this.importeHora = importeHora;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public Integer getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(Integer efectivo) {
		this.efectivo = efectivo;
	}

	public String getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Integer getNumeroListado() {
		return numeroListado;
	}

	public void setNumeroListado(Integer numeroListado) {
		this.numeroListado = numeroListado;
	}
	
	
	
	
	
	
	
	

}
