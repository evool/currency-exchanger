package repository;

import javax.persistence.EntityManager;

import model.Currency;

public class CurrencyRepositoryImpl implements CurrencyRepository {

	private EntityManager em;

	public CurrencyRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Currency getCurrencyById(Long id) {
		return em.find(Currency.class, id);
	}

	@Override
	public Currency saveCurrency(Currency currency) {
		if (currency.getId() == null) {
			em.persist(currency);
		} else {
			currency = em.merge(currency);
		}
		return currency;
	}

	@Override
	public void deleteCurrency(Currency currency) {
		if (em.contains(currency)) {
			em.remove(currency);
		} else {
			em.merge(currency);
		}
	}
}