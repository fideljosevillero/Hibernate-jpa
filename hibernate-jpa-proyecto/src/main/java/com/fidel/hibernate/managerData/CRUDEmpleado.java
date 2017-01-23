package com.fidel.hibernate.managerData;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;

import com.fidel.hibernate.modelo.Autor;
import com.fidel.hibernate.modelo.Direccion;
import com.fidel.hibernate.modelo.Empleado;
import com.fidel.hibernate.modelo.Libro;

public class CRUDEmpleado {

//	@PersistenceContext(unitName="mi_persistencia") //CON JAVA EE CON EJB
//	private static EntityManager manager;
//	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
//		guardarEmpleado();
		
		// buscar Empleado
//		Empleado emp = buscarEmpleado(2L);
//		emp.toString();
		
		// Eliminar empleado
//		if(removeEmpleado(1L)){
//			System.out.println("Elemento eliminado satisfactoriamente !!!");
//		}else{
//			System.out.println("No se pudo borrar el elemento!!!");
//		}
		// Relacion bidireccional
//		getDireccionByEmpleado(1L);
		
		// Relacion uno a muchos
//		guardarAutorAndLibros();
		
		buscarAutor(3L);
		
//		buscarLibros(1L);
		
		//imprimir();
	}
	
	public static boolean guardarEmpleado(){
		EntityManager manager;
		System.out.println("1");
		EntityManagerFactory emf;
		System.out.println("1.2");
		emf = Persistence.createEntityManagerFactory("mi_persistencia");
		System.out.println("2");
		manager = emf.createEntityManager();
		try{			
			Empleado e = new Empleado(null, "Fidel", "Villero", new GregorianCalendar(1985, 8, 17).getTime(),
							new Direccion(null, "Tolú", "Sucre", "Colombia"));
			
			manager.getTransaction().begin();
			manager.persist(e);
			manager.getTransaction().commit();
			e.setNombre("segundo nombre");
			manager.close();
			return true;
		}catch(Exception er){
			System.out.println("error "+er.toString());
			manager.getTransaction().rollback();
			manager.close();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void imprimir(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mi_persistencia");
		EntityManager manager = emf.createEntityManager();
		List<Empleado> lista = (List<Empleado>) manager.createQuery("FROM Empleado").getResultList();
		System.out.println("LA DB TIENE "+ lista.size()+" EMPLEADO(S)!!!");
		for (Empleado emp : lista) {
			emp.toString();
		}
		manager.close();
	}

	public static Empleado buscarEmpleado(Long id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mi_persistencia");
		EntityManager manager = emf.createEntityManager();
		Empleado emp = new Empleado();
		try{
			System.out.println("1");
			manager.getTransaction().begin();
			emp = manager.find(Empleado.class, id);
			manager.getTransaction().commit();
			System.out.println("=== "+emp);
			manager.close();
		}catch(Exception er){
			manager.getTransaction().rollback();
			manager.close();
			System.out.println("error en buscarEmpleado "+er.toString());
		}
		return emp;
	}
	
	public static boolean removeEmpleado(Long id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mi_persistencia");
		EntityManager manager = emf.createEntityManager();
		try{			
			manager.getTransaction().begin();
			Empleado emp = buscarEmpleado(id);
			if(emp == null){
				return false;
			}
			emp = manager.merge(emp);
			manager.remove(emp);
			manager.getTransaction().commit();
			manager.close();
			return true;
		}catch(Exception er){
			System.out.println("error al eliminar "+er.toString());
			manager.getTransaction().rollback();
			manager.close();
			return false;
		}
	}
	
	public static void getDireccionByEmpleado(Long id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mi_persistencia");
		EntityManager manager = emf.createEntityManager();
		Direccion d = manager.find(Direccion.class, id);
		System.out.println("Empleado de esta direccion Direccion "+d.getClass().getName());
	}
	
	public static void guardarAutorAndLibros(){
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mi_persistencia");
			EntityManager manager = emf.createEntityManager();
			Autor a = new Autor(null, "J. K. Rowling", 24, null);
			
			Libro l = new Libro(null, "Harry Potter", new GregorianCalendar(1997, 01, 01).getTime(), a);
			Libro l1 = new Libro(null, "Harry Potter 2", new GregorianCalendar(1998, 01, 01).getTime(), a);
			Libro l2 = new Libro(null, "Harry Potter 3", new GregorianCalendar(1999, 01, 01).getTime(), a);
			
			manager.getTransaction().begin();
//			manager.persist(a);
			manager.persist(l1);
			manager.persist(l2);
			manager.getTransaction().commit();
			
			manager.close();
		}catch(Exception er){
			System.out.println("error en guardarAutorAndLibros " + er.toString());
		}
	}
	
	public static void buscarAutor(Long id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mi_persistencia");
		EntityManager manager = emf.createEntityManager();
		Autor autor = manager.find(Autor.class, id);
		manager.close();
		System.out.println("el primer libro del autor "+autor.getNombre() + " es " + autor.getLibros().get(0).getNombre());
	}
	
	public static void buscarLibros(Long id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mi_persistencia");
		EntityManager manager = emf.createEntityManager();
		Libro libro = manager.find(Libro.class, id);
		manager.close();
		System.out.println("El autor del libro " + libro.getNombre() + " es " + libro.getAutor().getNombre());
	}
	
}
