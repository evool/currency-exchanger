package repository;

import java.time.LocalDate;
import java.util.List;

import entity.RateEntity;
import model.CurrencyCode;

public interface RateRepository {
	RateEntity getRateById(Long id);
	List<RateEntity> getRatesByCurrencyCode(CurrencyCode code);
	void saveRate(RateEntity rate);
	List<RateEntity> getRatesByDate(LocalDate date);
}