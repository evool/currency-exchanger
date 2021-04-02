package service.provider;

import java.time.LocalDate;

import cache.CurrencyCacheUtils;
import model.CurrencyCode;

public class CacheProvider implements Providing {

	@Override
	public Object find(CurrencyCode code, LocalDate date) {
		return CurrencyCacheUtils.get().getIfFound(code, date);
	}
}