package service.provider;

import java.time.LocalDate;

import cache.CurrencyCacheUtils;
import model.Currency;
import model.CurrencyCode;

public class CacheProvider implements Providing {

	@Override
	public Currency find(CurrencyCode code, LocalDate date) {
		return CurrencyCacheUtils.get().find(code, date);
	}
}