package model;

import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

// moznaby zaimplementowac gotowca np. guava cache
// ponoc niemal zawsze ostatecznie wychodzi to na lepsze

public class Cache<T> {
	private int maxCacheSize = 1024;
	private ConcurrentLinkedQueue<T> cache = new ConcurrentLinkedQueue<>();

	public Cache() {
	}

	public Cache(int maxCacheSize) {
		this.maxCacheSize = maxCacheSize;
	}

	public void add(T currency) {
		if (cache.size() >= maxCacheSize) {
			cache.poll();
		}
		cache.add(currency);
	}

	public Optional<T> getIfFound(T object) {
		Iterator<T> i = cache.iterator();
		Optional<T> temp;
		while (i.hasNext()) {
			temp = Optional.of(i.next());
			if (temp.get().equals(object)) {
				return temp;
			}
		}
		return Optional.empty();
	}
}