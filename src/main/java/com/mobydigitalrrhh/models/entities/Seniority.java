package com.mobydigitalrrhh.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Seniority")
public class Seniority implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_seniority")
	private Integer idSeniority;

	@NotBlank(message = "el campo descripcion debe tener almenos un caracter")
	private String descripcion;

	
	
	public Integer getIdSeniority() {
		return idSeniority;
	}

	public void setIdSeniority(Integer idSeniority) {
		this.idSeniority = idSeniority;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private static final long serialVersionUID = 1L;

}