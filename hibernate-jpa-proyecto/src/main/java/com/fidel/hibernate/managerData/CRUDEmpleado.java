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
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		guardarEmpleado();
		imprimir();
	}
	
	public static boolean guardarEmpleado(){
		emf = Persistence.createEntityManagerFactory("mi_persistencia");
		manager = emf.createEntityManager();
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
			
			return true;
		}catch(Exception er){
			System.out.println("error "+er.toString());
			manager.getTransaction().rollback();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void imprimir(){
		List<Empleado> lista = (List<Empleado>) manager.createQuery("FROM Empleado").getResultList();
		System.out.println("LA DB TIENE "+ lista.size()+" EMPLEADOS!!!");
		for (Empleado emp : lista) {
			emp.toString();
		}
	}

}
