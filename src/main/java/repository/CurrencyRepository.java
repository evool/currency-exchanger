package repository;

import model.Currency;

public interface CurrencyRepository {
	Currency getCurrencyById(Long id);
	Currency saveCurrency(Currency currency);
	void deleteCurrency(Currency currency);
}
