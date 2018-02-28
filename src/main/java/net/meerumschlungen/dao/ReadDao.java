package net.meerumschlungen.dao;

import java.util.List;

public interface ReadDao<T> {
	public List<T> read();
}
