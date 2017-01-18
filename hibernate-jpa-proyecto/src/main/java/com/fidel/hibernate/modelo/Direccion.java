package com.fidel.hibernate.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DIRECCION")
public class Direccion implements Serializable {

	public Direccion(Long id, String provincia, String departemento, String pais) {
		this.id = id;
		this.provincia = provincia;
		this.departemento = departemento;
		this.pais = pais;
	}
	public Direccion() {
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="privincia")
	private String provincia;
	
	@Column(name="departamento")
	private String departemento;
	
	@Column(name="pais")
	private String pais;
	
}
