package br.edu.bahiana.core.datainterfaces;

public interface AccessDataObject {
	void save(Object transientObject, PersistentManager persistentManager);
	void saveOrUpdate(Object object, PersistentManager persistentManager);
	void update(Object persistentObject, PersistentManager persistentManager);
	void delete(Object persistentObject, PersistentManager persistentManager);
}
