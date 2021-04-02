package cache;

import model.Currency;

public class CurrencyCacheUtils {

	private static Caching<Currency> cache = new CurrencyCache();
	
	public static Caching<Currency> get() {
		return cache;
	}	
}