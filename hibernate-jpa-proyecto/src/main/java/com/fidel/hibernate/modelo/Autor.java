package com.fidel.hibernate.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="autor")
public class Autor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="autor_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long autorId;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="edad")
	private int edad;
	
	@OneToMany(mappedBy="autor", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Libro> libros;

	public Autor() {
	}
	
	public Autor(Long autorId, String nombre, int edad, List<Libro> libros) {
		this.autorId = autorId;
		this.nombre = nombre;
		this.edad = edad;
		this.libros = libros;
	}


	public Long getAutorId() {
		return autorId;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}



}
