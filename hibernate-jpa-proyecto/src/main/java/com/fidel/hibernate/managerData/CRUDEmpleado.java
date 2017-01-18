package com.fidel.hibernate.managerData;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;

import com.fidel.hibernate.modelo.Direccion;
import com.fidel.hibernate.modelo.Empleado;

public class CRUDEmpleado {

//	@PersistenceContext(unitName="mi_persistencia") //CON JAVA EE CON EJB
//	private static EntityManager manager;
//	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		guardarEmpleado();
		
		// buscar Empleado
//		Empleado emp = buscarEmpleado(6L);
//		emp.toString();
		
		// Eliminar empleado
//		if(removeEmpleado(3L)){
//			System.out.println("Elemento eliminado satisfactoriamente !!!");
//		}else{
//			System.out.println("No se pudo borrar el elemento!!!");
//		}
		
		imprimir();
	}
	
	public static boolean guardarEmpleado(){
		EntityManager manager;
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("mi_persistencia");
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
		System.out.println("LA DB TIENE "+ lista.size()+" EMPLEADOS!!!");
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
	
}
