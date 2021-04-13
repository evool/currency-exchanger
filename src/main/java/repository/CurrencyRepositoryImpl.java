package repository;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.CurrencyEntity;
import entity.CurrencyEntity;
import model.CurrencyCode;

public class CurrencyRepositoryImpl implements CurrencyRepository {

	EntityManager em;
	
	private EntityManager getEntityManager() {
		return EntityManagerFacotryUtil.getFactory().createEntityManager();
	}
	
	@Override
	public CurrencyEntity getCurrencyById(Long id) {
		em = getEntityManager();
		CurrencyEntity cur = em.find(CurrencyEntity.class, id);
		em.close();
		return cur;
	}

	@Override
	public CurrencyEntity getCurrencyByCode(CurrencyCode code) {
		em = getEntityManager();
		TypedQuery<CurrencyEntity> q = em.createQuery("SELECT c FROM CurrencyEntity c WHERE c.code = :code", CurrencyEntity.class);
		q.setParameter("code", code);
		CurrencyEntity currency = q.getSingleResult();
		em.close();
		return currency;
	}

	@Override
	public void saveCurrency(CurrencyEntity currency) {
		em = getEntityManager();
		em.getTransaction().begin();
//		if(currency.getId() == null) {
//			em.persist(currency);
//		} else {
		em.merge(currency);
//		}
		em.getTransaction().commit();
		em.close();
	}
	
//	@Override
//	public void saveCurrency(CurrencyEntity currency) {
//		em = getEntityManager();
//		em.getTransaction().begin();
//		TypedQuery<CurrencyEntity> q = em.createQuery("SELECT c FROM CurrencyEntity c WHERE c.code = :code", CurrencyEntity.class);
//		q.setParameter("code", currency.getCode());
//		try {
//			CurrencyEntity temp = q.getSingleResult();
//			temp.addRates(currency.getRates());
//			em.getTransaction().commit();
//		} catch(NoResultException e) {
//			em.persist(currency);
//			em.getTransaction().commit();
//		} finally {
//			em.close();
//		}
//	}

	@Override
	public CurrencyEntity find(CurrencyCode code, LocalDate date) {
		em = getEntityManager();
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