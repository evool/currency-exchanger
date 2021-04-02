package cache;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// moznaby zaimplementowac gotowca np. guava cache
// ponoc niemal zawsze ostatecznie wychodzi to na lepsze

@NoArgsConstructor
public class Cache<T> implements Caching<T> {
	@Getter @Setter
	private int maxSize = 1024;
	@Getter
	protected ConcurrentLinkedQueue<T> cache = new ConcurrentLinkedQueue<>();
	
	public Cache(int maxSize) {
		if(maxSize < 1) {
			throw new IllegalArgumentException("Cache size cannot be less than 1.");
		}
		this.maxSize = maxSize;
	}
	
	public void add(T obj) {
		while(cache.size() > maxSize) {
			cache.poll();
		}
		Iterator<T> i = cache.iterator();
		while (i.hasNext()) {
			if (i.next().equals(obj)) {
				return;
			}
		}
		cache.add(obj);
	}

	public T find(T obj) {
		Iterator<T> i = cache.iterator();
		T temp;
		while (i.hasNext()) {
			temp = i.next();
			if (temp.equals(obj)) {
				return temp;
			}
		}
		return null;
	}
	
	public void printAll() {
		Iterator<T> i = cache.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}
}