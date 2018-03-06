package net.meerumschlungen.dao;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface ReadDao<T> {
	public List<T> read();
	public default List<T> read(final Predicate<T> condition) {
        return this.read().stream().filter(condition).collect(Collectors.toList());
	}
}
