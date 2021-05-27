package repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("thePersistenceUnit");
	private static EntityManager em;
	
	public static void startEntityManager() {
		em = emf.createEntityManager();
	}
	
	public static EntityManager getEntityManager() {
		return em;
	}
	
	public static void closeEntityManager() {
		em.close();
	}
}