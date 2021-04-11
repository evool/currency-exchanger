package service.saver;

import entity.CurrencyEntity;
import repository.CurrencyRepositoryImpl;

public class DatabaseSaver implements Saving{

	@Override
	public void save(CurrencyEntity currency) {
		new CurrencyRepositoryImpl().save(currency);
	}
	
}
