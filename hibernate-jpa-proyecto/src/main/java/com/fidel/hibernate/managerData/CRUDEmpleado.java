package com.fidel.hibernate.managerData;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;

import com.fidel.hibernate.modelo.Empleado;

public class CRUDEmpleado {

//	@PersistenceContext(unitName="mi_persistencia") //CON JAVA EE CON EJB
//	private static EntityManager manager;
//	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		guardarEmpleado();
		
//		// buscar Empleado
//		Empleado emp = buscarEmpleado(1L);
//		System.out.println("--- "+emp);
//		//emp.toString();
//		
//		// Eliminar empplado
////		if(removeEmpleado(2L)){
////			System.out.println("Elemento eliminado satisfactoriamente !!!");
////		}else{
////			System.out.println("Error al intentar borrar el elemento!!!");
////		}
//		
//		imprimir();
	}
	
	public static boolean guardarEmpleado(){
		EntityManager manager;
		System.out.println("1");
		EntityManagerFactory emf;
		System.out.println("2");
		emf = Persistence.createEntityManagerFactory("mi_persistencia");
		System.out.println("3");
		manager = emf.createEntityManager();
		System.out.println("4");
		try{			
			//Empleado e = new Empleado(1L, "Fidel", "Villero", new GregorianCalendar(1985, 8, 17).getTime());
			Empleado e = new Empleado(null, "Fidel", "Villero", new GregorianCalendar(1985, 8, 17).getTime());
			Empleado e1 = new Empleado(null, "Fidel", "Villero", new GregorianCalendar(1985, 8, 17).getTime());
			Empleado e2 = new Empleado(null, "Fidel", "Villero", new GregorianCalendar(1985, 8, 17).getTime());
			manager.getTransaction().begin();
			manager.persist(e);
			manager.persist(e1);
			manager.persist(e2);
			manager.getTransaction().commit();
			e.setNombre("segundo nombre");
			
			
			
			
			//manager.close();
			return true;
		}catch(Exception er){
			System.out.println("error "+er.toString());
			manager.getTransaction().rollback();
			//manager.close();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void imprimir(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mi_persistencia");
		EntityManager manager = emf.createEntityManager();
		List<Empleado> lista = (List<Empleado>) manager.createQuery("FROM Empleado").getResultList();
		System.out.println("LA DB TIENE "+ lista.size()+" EMPLEADOS!!!");
		for (Empleado emp : lista) {
			emp.toString();
		}
	}

	public static Empleado buscarEmpleado(Long id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mi_persistencia");
		EntityManager manager = emf.createEntityManager();
		Empleado emp = new Empleado();
		try{
			emf = Persistence.createEntityManagerFactory("mi_persistencia");
			manager = emf.createEntityManager();
			manager.getTransaction().begin();
			emp = manager.find(Empleado.class, id);
			manager.getTransaction().commit();
			//manager.close();
		}catch(Exception er){
			System.out.println("");
		}
		return emp;
	}
	
//	public static boolean removeEmpleado(Long id){
//		try{
//			emf = Persistence.createEntityManagerFactory("mi_persistencia");
//			manager = emf.createEntityManager();
//			
//			manager.getTransaction().begin();
//			Empleado emp = buscarEmpleado(id, manager);
//			manager.remove(emp);
//			manager.getTransaction().commit();
//			manager.close();
//			return true;
//		}catch(Exception er){
//			System.out.println("error al eliminar "+er.toString());
//			return false;
//		}
//	}
	
}
