package model;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import service.LoadingUtils;

// moznaby zaimplementowac gotowca np. guava cache
// ponoc niemal zawsze ostatecznie wychodzi to na lepsze

public class CurrencyCache {
	private static int maxCacheSize = 1024;
	private static ConcurrentLinkedQueue<Currency> cache = new ConcurrentLinkedQueue<>();

	public static void add(Currency currency) {
		if (cache.size() >= maxCacheSize) {
			cache.poll();
		}
		cache.add(currency);
	}

	public static Currency find(CurrencyCode code, LocalDate date) {
		date = LoadingUtils.verifyAndCorrectDate(date);
		Iterator<Currency> i = cache.iterator();
		Currency temp;
		while (i.hasNext()) {
			temp = i.next();
			if (temp.getCode() == code && temp.getEffectiveDate().equals(date)) {
				return temp;
			}
		}
		return null;
	}
}