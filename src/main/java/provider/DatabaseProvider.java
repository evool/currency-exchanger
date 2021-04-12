package provider;

import java.time.LocalDate;

import entity.CurrencyEntity;
import model.CurrencyCode;
import repository.CurrencyRepositoryImpl;

public class DatabaseProvider implements Providing<CurrencyEntity> {
	
	@Override
	public CurrencyEntity find(CurrencyCode code, LocalDate date) {
		return new CurrencyRepositoryImpl().find(code, date);
	}
}