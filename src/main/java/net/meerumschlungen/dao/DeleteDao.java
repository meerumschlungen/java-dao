package net.meerumschlungen.dao;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface DeleteDao<T> extends ReadDao<T> {
	public int delete(final T dto);
	public default int delete(final Collection<T> dtos) {
		return dtos.stream().mapToInt(dto -> this.delete(dto)).sum();
	}
	public default int delete() {
		return this.delete(this.read());
	}
	public default int delete(final Predicate<T> condition) {
		return this.delete(this.read().stream().filter(condition).collect(Collectors.toList()));
	}
}
