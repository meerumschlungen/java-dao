package net.meerumschlungen.dao;

public abstract class AbstractReadWriteDao<T> implements ReadDao<T>, CreateDao<T>, UpdateDao<T>, ReplaceDao<T> {
	
}
