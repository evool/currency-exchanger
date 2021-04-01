package service;

import java.time.LocalDate;

import model.CurrencyCode;
import repository.CurrencyRepositoryImpl;

public class DatabaseProvider implements Providing {
	
	@Override
	public Object find(CurrencyCode code, LocalDate date) {
		return new CurrencyRepositoryImpl().getCurrency(code, date);
	}

}
