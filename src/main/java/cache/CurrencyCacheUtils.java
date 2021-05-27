package cache;

public class CurrencyCacheUtils {

	private static CurrencyCache cache = new CurrencyCache();
	
	public static CurrencyCache getCache() {
		return cache;
	}	
}