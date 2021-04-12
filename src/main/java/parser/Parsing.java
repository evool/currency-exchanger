package parser;

public interface Parsing<T, S> {
	S parse(T data);
}
