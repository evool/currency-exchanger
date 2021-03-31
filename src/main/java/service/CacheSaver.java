package service;

import model.Currency;
import model.CurrencyCache;

public class CacheSaver implements Saving {

	@Override
	public void save(Currency currency) {
		CurrencyCache.add(currency);
	}
}