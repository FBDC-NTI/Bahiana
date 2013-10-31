package br.edu.bahiana.core.datainterfaces;

import java.util.UUID;

public interface AccessDataObjectFactory {
	AccessDataObject createDataAcessObject();
	PersistentManager createPersistentManager();	
	<T> AccessDataObjectList<T> createDataAcessObjectList(PersistentManager manager);
}
