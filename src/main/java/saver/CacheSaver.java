package saver;

import cache.CurrencyCacheUtils;
import entity.CurrencyEntity;

public class CacheSaver implements Saving<CurrencyEntity> {

	@Override
	public void save(CurrencyEntity currency) {
		CurrencyCacheUtils.get().save(currency);
	}
}