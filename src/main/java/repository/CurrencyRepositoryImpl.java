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
	public CurrencyEntity get(Long id) {
		EntityManager em = getEntityManager();
		CurrencyEntity cur = em.find(CurrencyEntity.class, id);
		em.close();
		return cur;
	}

	@Override
	public CurrencyEntity save(CurrencyEntity currency) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		if (currency.getId() == null) {
			em.persist(currency);
		} else {
			currency = em.merge(currency);
		}
		em.getTransaction().commit();
		em.close();
		return currency;
	}

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
		TypedQuery<CurrencyEntity> q = em.createQuery(
				"SELECT c FROM Currency c WHERE c.code = :code AND c.effectiveDate = :date", CurrencyEntity.class);
		q.setParameter("code", code);
		q.setParameter("date", date);
		try {
			return q.getSingleResult();			
		} catch(NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
}