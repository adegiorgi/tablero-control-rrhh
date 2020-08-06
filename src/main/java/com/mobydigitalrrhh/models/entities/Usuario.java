package com.mobydigitalrrhh.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@NotBlank(message = "El campo e-mail no puede estar vacío")
	@Email(message = "Ingrese un e-mail válido")
	private String email;

	@NotBlank(message = "El campo nombre no puede estar vacío")
	private String nombre;

	@NotBlank(message = "El campo apellido no puede estar vacío")
	private String apellido;

	@NotBlank(message = "El campo imagen no puede estar vacío")
	@Column(name = "imagen_url")
	private String imagenUrl;

	@NotBlank(message = "El campo token no puede estar vacío")
	private String token;

	@OneToMany(mappedBy = "usuario", fetch=FetchType.EAGER)
	private List<UsuarioPorRol> usuarioPorRoles;

	public List<UsuarioPorRol> getUsuarioPorRoles() {
		return usuarioPorRoles;
	}

	public void setUsuarioPorRoles(List<UsuarioPorRol> usuarioPorRoles) {
		this.usuarioPorRoles = usuarioPorRoles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private static final long serialVersionUID = 1L;

}
