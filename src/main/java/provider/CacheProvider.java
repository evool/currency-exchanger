package provider;

import java.time.LocalDate;

import cache.CurrencyCacheUtils;
import entity.CurrencyEntity;
import model.CurrencyCode;

public class CacheProvider implements Providing {

	@Override
	public CurrencyEntity find(CurrencyCode code, LocalDate date) {
		return CurrencyCacheUtils.get().find(code, date);
	}
}