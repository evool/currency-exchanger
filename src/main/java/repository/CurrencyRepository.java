package repository;

import java.time.LocalDate;

import model.Currency;
import model.CurrencyCode;

public interface CurrencyRepository {
	Currency getCurrencyById(Long id);
	Currency getCurrency(CurrencyCode code, LocalDate date);
	Currency saveCurrency(Currency currency);
//	void deleteCurrency(Currency currency);
}