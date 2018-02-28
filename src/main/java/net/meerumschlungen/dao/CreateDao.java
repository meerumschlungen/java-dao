package net.meerumschlungen.dao;

import java.util.Collection;

public interface CreateDao<T> {
	public int create(T dto);
	public default int create(Collection<T> dtos) {
		return dtos.stream().mapToInt(dto -> this.create(dto)).sum();
	}
}
