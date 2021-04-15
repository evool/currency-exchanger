package repository;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.CurrencyEntity;
import model.CurrencyCode;

public class CurrencyRepositoryImpl implements CurrencyRepository {

	private EntityManager em = EntityManagerUtil.getEntityManager();
	
	@Override
	public CurrencyEntity getCurrencyById(Long id) {
		CurrencyEntity cur = em.find(CurrencyEntity.class, id);
		return cur;
	}

	@Override
	public CurrencyEntity getCurrencyByCode(CurrencyCode code) {
		TypedQuery<CurrencyEntity> q = em.createQuery("SELECT c FROM CurrencyEntity c WHERE c.code = :code", CurrencyEntity.class);
		q.setParameter("code", code);
		CurrencyEntity currency = q.getSingleResult();
		return currency;
	}

	@Override
	public void saveCurrency(CurrencyEntity currency) {
		em.getTransaction().begin();
		if(currency.getId() == null) {
			em.persist(currency);
		} else {
			em.merge(currency);
		}
		em.getTransaction().commit();
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
		}
	}
}