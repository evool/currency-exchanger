package repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFacotryUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("thePersistenceUnit");
	
	public static EntityManagerFactory getFactory() {
		return emf;
	}
}
