package parser;

public interface Parsing<T, S> {
	T parse(S data);
}
