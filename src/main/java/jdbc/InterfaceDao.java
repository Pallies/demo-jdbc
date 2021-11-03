package jdbc;

import java.util.List;

public interface InterfaceDao<T> {

	public List<T> getAll();

	public T find(T table);

	public void insert(T table);

	public int update(T tableOld, T tableNew);

	public int delete(T table);

}
