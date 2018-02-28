package net.meerumschlungen.dao;

import java.util.Collection;

public interface ReplaceDao<T> extends UpdateDao<T>, CreateDao<T> {
	public default int replace(T dto) {
		int n = this.update(dto);
		if (n < 1) n = this.create(dto);
		return n;
	}
	public default int replace(Collection<T> dtos) {
		return dtos.stream().mapToInt(dto -> this.replace(dto)).sum();
	}
}
