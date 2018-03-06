package net.meerumschlungen.dao;

import java.util.Collection;

public interface UpdateDao<T> {
	public int update(final T dto);
	public default int update(final Collection<T> dtos) {
		return dtos.stream().mapToInt(dto -> this.update(dto)).sum();
	}
}
