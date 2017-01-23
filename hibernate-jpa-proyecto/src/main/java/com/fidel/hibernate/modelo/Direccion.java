package com.fidel.hibernate.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DIRECCION")
public class Direccion implements Serializable {

	private static final long serialVersionUID = 1L;

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
	
	@JoinColumn(name="empleado_id")
	@OneToOne(cascade=CascadeType.ALL, mappedBy="direccion", fetch=FetchType.EAGER)
	private Empleado empleado;
	
	public Direccion(Long id, String provincia, String departemento, String pais) {
		this.id = id;
		this.provincia = provincia;
		this.departemento = departemento;
		this.pais = pais;
	}

	public Direccion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDepartemento() {
		return departemento;
	}

	public void setDepartemento(String departemento) {
		this.departemento = departemento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", provincia=" + provincia + ", departemento=" + departemento + ", pais=" + pais
				 + "]";
	}
	
}
