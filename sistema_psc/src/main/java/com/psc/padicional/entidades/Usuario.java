package com.psc.padicional.entidades;

import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name="users")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="username", unique=true, nullable = false, length = 45)
	private String username;
	@Column(name="password", nullable = false, length = 60)
	private String password;
	@Column(name="enabled", nullable=false)
	private boolean enabled;
	@Column(name="nombre")
	private String nombre;
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="user")
	private Set<UserRole> userRole = new HashSet<>();
	
	@Column(name="id_codigo_gestion")
	private Integer id_codigo_gestion;
	@ManyToOne(optional=true)
	@JoinColumn(name="id_codigo_gestion",referencedColumnName="id", insertable = false, updatable = false)
	private CodigoGestion codigoGestion;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Set<UserRole> getUserRole() {
		return userRole;
	}
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	public Usuario(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	public Usuario(String username, String password, boolean enabled, Set<UserRole> userRole, String nombre) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
		this.nombre=nombre;
	}
	
	public Usuario() {}
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
	
	
	
	
	
	

}
