package cache;

public interface Caching<T> {
	T find(T obj);
	void add(T obj);
}