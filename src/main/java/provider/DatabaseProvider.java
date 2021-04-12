package provider;

import java.time.LocalDate;

import entity.CurrencyEntity;
import model.CurrencyCode;
import repository.CurrencyRepositoryImpl;

public class DatabaseProvider implements Providing<CurrencyEntity> {
	
	@Override
	public CurrencyEntity find(CurrencyCode code, LocalDate date) {
		CurrencyEntity currency = new CurrencyRepositoryImpl().find(code, date);
		if(currency != null) {
			if(currency.getRate(date) != null) {
				return currency;				
			}
		}
		return null;
	}
}