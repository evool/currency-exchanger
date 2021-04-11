package service.saver;

import cache.CurrencyCacheUtils;
import entity.CurrencyEntity;

public class CacheSaver implements Saving {

	@Override
	public void save(CurrencyEntity currency) {
		CurrencyCacheUtils.get().add(currency);
	}
}