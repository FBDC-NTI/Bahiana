package br.edu.bahiana.core.datainterfaces;

import java.util.List;

public interface AccessDataObjectList<T> {
	List<T> getObjects();
	List<T> getObjects(int size);
	List<T> getObjects(int start, int size);
	T getObject(long id, Class<T> clazz);
	
}
