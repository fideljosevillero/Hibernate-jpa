package com.fidel.hibernate.managerData;

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
		try{
		System.out.println("prueba 1");
		emf = Persistence.createEntityManagerFactory("mi_persistencia");
		System.out.println("prueba 2");
		manager = emf.createEntityManager();
		
		List<Empleado> lista = (List<Empleado>) manager.createQuery("FROM Empleado").getResultList();
		System.out.println("LA DB TIENE "+ lista.size()+" EMPLEADOS!!!");
		}catch(Exception er){
			System.out.println("error "+er.toString());
		}
	}

}
