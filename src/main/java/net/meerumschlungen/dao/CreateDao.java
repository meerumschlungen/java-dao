package net.meerumschlungen.dao;

import java.util.Collection;

public interface CreateDao<T> {
	public int create(final T dto);
	public default int create(final Collection<T> dtos) {
		return dtos.stream().mapToInt(dto -> this.create(dto)).sum();
	}
}
