package repository;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.CurrencyEntity;
import model.CurrencyCode;

public class CurrencyRepositoryImpl implements CurrencyRepository {

	
	
	private EntityManager getEntityManager() {
		return EntityManagerFacotryUtil.getFactory().createEntityManager();
	}
	
	@Override
	public CurrencyEntity getById(Long id) {
		EntityManager em = getEntityManager();
		CurrencyEntity cur = em.find(CurrencyEntity.class, id);
		em.close();
		return cur;
	}

	@Override
	public void save(CurrencyEntity currency) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		TypedQuery<CurrencyEntity> q = em.createQuery("SELECT c FROM CurrencyEntity c WHERE c.code = :code", CurrencyEntity.class);
		q.setParameter("code", currency.getCode());
		try {
			CurrencyEntity temp = q.getSingleResult();
			temp.addRates(currency.getRates());
			em.getTransaction().commit();
		} catch(NoResultException e) {
			em.persist(currency);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
//	@Override
//	public void save(CurrencyEntity currency) {
//	    EntityManager em = getEntityManager(); 
//	    em.getTransaction().begin();
//	    em.persist(currency);
//	    em.getTransaction().commit();
//	    em.close();
//	}

//	@Override
//	public void deleteCurrency(Currency currency) {
//		if (em.contains(currency)) {
//			em.remove(currency);
//		} else {
//			em.merge(currency);
//		}
//	}

	@Override
	public CurrencyEntity find(CurrencyCode code, LocalDate date) {
		EntityManager em = getEntityManager();
		TypedQuery<CurrencyEntity> q = em.createQuery("SELECT c FROM CurrencyEntity c WHERE c.code = :code", CurrencyEntity.class);
		q.setParameter("code", code);
		try {
			CurrencyEntity currency = q.getSingleResult();
			if(currency.getRate(date) != null) {
				return currency;
			}
			return null;
		} catch(NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
}