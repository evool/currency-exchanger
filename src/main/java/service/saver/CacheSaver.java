package service.saver;

import cache.CurrencyCacheUtils;
import model.Currency;

public class CacheSaver implements Saving {

	@Override
	public void save(Currency currency) {
		CurrencyCacheUtils.get().add(currency);
	}
}