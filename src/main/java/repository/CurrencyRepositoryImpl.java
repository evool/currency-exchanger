package repository;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Currency;
import model.CurrencyCode;

public class CurrencyRepositoryImpl implements CurrencyRepository {

	private EntityManager getEntityManager() {
		return EntityManagerFacotryUtil.getFactory().createEntityManager();
	}
	
	@Override
	public Currency getCurrencyById(Long id) {
		EntityManager em = getEntityManager();
		return em.find(Currency.class, id);
	}

	@Override
	public Currency saveCurrency(Currency currency) {
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
	public Currency getCurrency(CurrencyCode code, LocalDate date) {
		EntityManager em = getEntityManager();
		TypedQuery<Currency> q = em.createQuery(
				"SELECT c FROM Currency c WHERE c.currencyCode = :code AND c.effectiveDate = :date", Currency.class);
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