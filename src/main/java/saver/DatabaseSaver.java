package saver;

import entity.CurrencyEntity;
import repository.CurrencyRepositoryImpl;

public class DatabaseSaver implements Saving<CurrencyEntity> {

	@Override
	public void save(CurrencyEntity currency) {
		new CurrencyRepositoryImpl().save(currency);
	}
	
}
