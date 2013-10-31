package br.edu.bahiana.data.dataclasses;

import java.util.UUID;

import br.edu.bahiana.core.datainterfaces.AccessDataObjectList;
import br.edu.bahiana.data.managers.PersistentManager;


public class AccessDataObjectFactory implements br.edu.bahiana.core.datainterfaces.AccessDataObjectFactory {

	@Override
	public AccessDataObject createDataAcessObject() {		
		return new AccessDataObject();
	}

	@Override
	public PersistentManager createPersistentManager() {		
		return new PersistentManager();
	}

	@Override
	public <T> AccessDataObjectList<T> createDataAcessObjectList(br.edu.bahiana.core.datainterfaces.PersistentManager manager) {
		return new AccessDataObjectContainer<T>(manager);
	}

}
