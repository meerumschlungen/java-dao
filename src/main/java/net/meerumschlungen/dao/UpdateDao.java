package net.meerumschlungen.dao;

import java.util.Collection;

public interface UpdateDao<T> {
	public int update(T dto);
	public default int update(Collection<T> dtos) {
		return dtos.stream().mapToInt(dto -> this.update(dto)).sum();
	}
}
