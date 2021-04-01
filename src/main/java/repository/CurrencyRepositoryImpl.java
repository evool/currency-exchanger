package repository;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Currency;
import model.CurrencyCode;

public class CurrencyRepositoryImpl implements CurrencyRepository {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
	private static EntityManager em = factory.createEntityManager();

//	@Override
//	public Currency getCurrencyById(Long id) {
//		return em.find(Currency.class, id);
//	}

	@Override
	public Currency saveCurrency(Currency currency) {
		em.getTransaction().begin();
		if (currency.getId() == null) {
			em.persist(currency);
		} else {
			currency = em.merge(currency);
		}
		em.getTransaction().commit();
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
		TypedQuery<Currency> q = em.createQuery(
				"FROM Currency C WHERE C.currencyCode = :code AND C.effectiveDate = :date", Currency.class);
		q.setParameter("code", code);
		q.setParameter("date", date);
		try {
			return q.getSingleResult();			
		} catch(NoResultException e) {
			return null;
		}
	}
}