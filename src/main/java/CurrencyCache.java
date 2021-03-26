import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

import currency.Currency;
import currency.CurrencyCode;

public class CurrencyCache {

	private static final int MAX_CACHE_SIZE = 1000;
	private static HashSet<Currency> cache = new LinkedHashSet<>();
	
	public static void add(Currency currency) {
		if(cache.size() >= MAX_CACHE_SIZE) {
			cache.remove(cache.iterator().next());
		}
		cache.add(currency);
	}
	
	public static Optional<Currency> checkAndGetIfFound(CurrencyCode code, LocalDate date) {
		Iterator<Currency> i = cache.iterator();
		Optional<Currency> temp;
		while(i.hasNext()) {
			temp = Optional.of(i.next());
			if(temp.get().getCode() == code && temp.get().getEffectiveDate().equals(date)) {
				return temp;
			}
		}
		return Optional.empty();
	}
}
