package com.fidel.hibernate.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="libro")
public class Libro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="libro_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long libroId;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="autor_id")
	private Autor autor;

	public Libro() {
	}

	public Libro(Long libroId, String nombre, Date fechaCreacion, Autor autor) {
		this.libroId = libroId;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.autor = autor;
	}

	public Long getLibroId() {
		return libroId;
	}

	public void setLibroId(Long libroId) {
		this.libroId = libroId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	

}
