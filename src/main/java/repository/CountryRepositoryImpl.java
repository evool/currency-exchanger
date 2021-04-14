package repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.CountryEntity;

public class CountryRepositoryImpl implements CountryRepository {

	EntityManager em;
	
	private EntityManager getEntityManager() {
		return EntityManagerFacotryUtil.getFactory().createEntityManager();
	}
	
	@Override
	public CountryEntity getCountryById(Long id) {
		em = getEntityManager();
		CountryEntity country = em.find(CountryEntity.class, id);
		em.close();
		return country;
	}

	@Override
	public CountryEntity getCountryByName(String name) {
		em = getEntityManager();
		TypedQuery<CountryEntity> q = em.createQuery("SELECT c FROM CountryEntity c WHERE c.name = :name", CountryEntity.class);
		q.setParameter("name", name);
		CountryEntity country = q.getSingleResult();
		em.close();
		return country;
	}
	
	@Override
	public void saveCountry(CountryEntity country) {
		em = getEntityManager();
		em.getTransaction().begin();
//		if(country.getId() == null) {
//			em.persist(country);
//		} else {
			em.merge(country);
//		}
		em.getTransaction().commit();
		em.close();
	}
}
