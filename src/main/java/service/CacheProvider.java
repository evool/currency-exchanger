package service;

import java.time.LocalDate;

import model.CurrencyCache;
import model.CurrencyCode;

public class CacheProvider implements Providing {

	@Override
	public Object find(CurrencyCode code, LocalDate date) {
		return CurrencyCache.find(code, date);
	}
}