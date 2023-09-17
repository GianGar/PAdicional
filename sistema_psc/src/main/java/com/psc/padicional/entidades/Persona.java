package com.psc.padicional.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Persona")
@Table(name="leg_personas")
public class Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	@Column(name="nombre")
	String nombre;
	@Column(name="fecha_nacimiento")
	String fechaNacimiento;
	@Column(name="documento")
	String documento;
	@Column(name="tipo_documento")
	Integer tipoDocumento;
	@Column(name="localidad_nacimiento")
	Integer localidadNacimiento;
	@Column(name="vive")
	boolean vive;
	@Column(name="sexo")
	String sexo;
	@Column(name="cuit_prefijo")
	Integer cuitPrefijo;
	@Column(name="cuit_sufijo")
	Integer cuitSufijo;
	@Column(name="grupo_sanguineo")
	String grupoSanguineo;
	@Column(name="estado_civil")
	Integer estadoCivil;
	
	
	public Persona() {}

	public Persona(int id, String nombre, String fechaNacimiento, String documento, Integer tipoDocumento,
			Integer localidadNacimiento, boolean vive, String sexo, Integer cuitPrefijo, Integer cuitSufijo,
			String grupoSanguineo, Integer estadoCivil) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.localidadNacimiento = localidadNacimiento;
		this.vive = vive;
		this.sexo = sexo;
		this.cuitPrefijo = cuitPrefijo;
		this.cuitSufijo = cuitSufijo;
		this.grupoSanguineo = grupoSanguineo;
		this.estadoCivil = estadoCivil;
	}

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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getLocalidadNacimiento() {
		return localidadNacimiento;
	}

	public void setLocalidadNacimiento(Integer localidadNacimiento) {
		this.localidadNacimiento = localidadNacimiento;
	}

	public boolean isVive() {
		return vive;
	}

	public void setVive(boolean vive) {
		this.vive = vive;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getCuitPrefijo() {
		return cuitPrefijo;
	}

	public void setCuitPrefijo(Integer cuitPrefijo) {
		this.cuitPrefijo = cuitPrefijo;
	}

	public Integer getCuitSufijo() {
		return cuitSufijo;
	}

	public void setCuitSufijo(Integer cuitSufijo) {
		this.cuitSufijo = cuitSufijo;
	}

	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public Integer getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(Integer estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	

	
	
	

}
