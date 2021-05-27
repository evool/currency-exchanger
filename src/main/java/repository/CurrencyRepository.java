package repository;

import java.time.LocalDate;

import entity.CurrencyEntity;
import model.CurrencyCode;

public interface CurrencyRepository {
	CurrencyEntity getCurrencyById(Long id);
	CurrencyEntity getCurrencyByCode(CurrencyCode code);
	CurrencyEntity find(CurrencyCode code, LocalDate date);
	void saveCurrency(CurrencyEntity currency);
//	void update(CurrencyEntity currency);
//	void delete(CurrencyEntity currency);
}