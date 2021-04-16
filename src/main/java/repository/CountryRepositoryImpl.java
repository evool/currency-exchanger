package repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.CountryEntity;

public class CountryRepositoryImpl implements CountryRepository {

	EntityManager em = EntityManagerUtil.getEntityManager();
	
	@Override
	public CountryEntity getCountryById(Long id) {
		CountryEntity country = em.find(CountryEntity.class, id);
		return country;
	}

	@Override
	public CountryEntity getCountryByName(String name) {
		TypedQuery<CountryEntity> q = em.createQuery("SELECT c FROM CountryEntity c WHERE c.name = :name", CountryEntity.class);
		q.setParameter("name", name);
		CountryEntity country = q.getSingleResult();
		return country;
	}
	
	@Override
	public void saveCountry(CountryEntity country) {
		em.getTransaction().begin();
		if(country.getId() == null) {
			em.persist(country);
		} else {
			em.merge(country);
		}
		em.getTransaction().commit();
	}
}
