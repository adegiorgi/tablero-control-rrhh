package com.mobydigitalrrhh.models.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "entrevistas")
public class Entrevista implements Serializable {

	@Id
	@Column(name = "id_entrevista")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idEntrevista;

	@NotBlank(message = "El campo fecha de contacto no puede estar vacío")
	@Column(name = "fecha_contacto")
	private Date fechaContacto;

	@NotBlank(message = "El campo medio de contacto no puede estar vacío")
	@Column(name = "medio_contacto")
	private String medioContacto;

	@Column(name = "pretension_salarial", scale = 2)
	private Float pretensionSalarial;

	public Integer getIdEntrevista() {
		return idEntrevista;
	}

	public void setIdEntrevista(Integer idEntrevista) {
		this.idEntrevista = idEntrevista;
	}

	public Date getFechaContacto() {
		return fechaContacto;
	}

	public void setFechaContacto(Date fechaContacto) {
		this.fechaContacto = fechaContacto;
	}

	public String getMedioContacto() {
		return medioContacto;
	}

	public void setMedioContacto(String medioContacto) {
		this.medioContacto = medioContacto;
	}

	public Float getPretensionSalarial() {
		return pretensionSalarial;
	}

	public void setPretensionSalarial(Float pretensionSalarial) {
		this.pretensionSalarial = pretensionSalarial;
	}

	private static final long serialVersionUID = 1L;
}