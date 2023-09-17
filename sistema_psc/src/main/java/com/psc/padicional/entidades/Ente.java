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

@Entity(name="Ente")
@Table(name="pa_entes")
public class Ente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="tipo")
	private String tipo;
	@Column(name="nombre")
	private String nombre;
	@Column(name="direccion")
	private String direccion;
	@Column(name="responsable")
	private String responsable;
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="id_categ")
	private Integer id_categ;
	@ManyToOne(optional=false)
	@JoinColumn(name="id_categ",referencedColumnName="id", insertable = false, updatable = false)
	private Categoria categoria;
	
	@Column(name="estado")
	private String estado;
	@Column(name="foto")
	private String foto;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="ente")
	private List<Objetivo> objetivos;
	
	@Column(name="id_codigo_gestion")
	private Integer id_codigo_gestion;
	@ManyToOne(optional=true)
	@JoinColumn(name="id_codigo_gestion",referencedColumnName="id", insertable = false, updatable = false)
	private CodigoGestion codigoGestion;
	
	public Ente() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getId_categ() {
		return id_categ;
	}

	public void setId_categ(Integer id_categ) {
		this.id_categ = id_categ;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Objetivo> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
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
	
	

	@Override
	public String toString() {
		return "Ente [id=" + id + ", tipo=" + tipo + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", responsable=" + responsable + ", telefono=" + telefono + ", id_categ=" + id_categ + ", categoria="
				+ categoria + ", estado=" + estado + ", foto=" + foto + ", objetivos=" + objetivos
				+ ", id_codigo_gestion=" + id_codigo_gestion + ", codigoGestion=" + codigoGestion + "]";
	}
	
	

	
	
	
	


}
