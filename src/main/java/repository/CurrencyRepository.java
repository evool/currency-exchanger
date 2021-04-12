package repository;

import java.time.LocalDate;

import entity.CurrencyEntity;
import model.CurrencyCode;

public interface CurrencyRepository {
	CurrencyEntity getById(Long id);
	CurrencyEntity find(CurrencyCode code, LocalDate date);
	void save(CurrencyEntity currency);
//	void update(CurrencyEntity currency);
//	void delete(CurrencyEntity currency);
}