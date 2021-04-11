package service.saver;

import model.Currency;
import repository.CurrencyRepositoryImpl;

public class DatabaseSaver implements Saving{

	@Override
	public void save(Currency currency) {
		new CurrencyRepositoryImpl().save(currency);
	}
	
}
