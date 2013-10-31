package br.edu.bahiana.data.dataclasses;

import br.edu.bahiana.core.datainterfaces.PersistentManager;


public class AccessDataObject implements br.edu.bahiana.core.datainterfaces.AccessDataObject {
	
	
	@Override
	public void save(Object transientObject, PersistentManager persistentManager) {
		br.edu.bahiana.data.managers.PersistentManager manager = (br.edu.bahiana.data.managers.PersistentManager)persistentManager;
		manager.save(transientObject);
		
	}

	@Override
	public void update(Object persistentObject,
			PersistentManager persistentManager) {
		br.edu.bahiana.data.managers.PersistentManager manager = (br.edu.bahiana.data.managers.PersistentManager)persistentManager;
		manager.update(persistentObject);
	}

	@Override
	public void delete(Object persistentObject,
			PersistentManager persistentManager) {
		br.edu.bahiana.data.managers.PersistentManager manager = (br.edu.bahiana.data.managers.PersistentManager)persistentManager;
		manager.delete(persistentObject);
	}
	@Override
	public void saveOrUpdate(Object object, PersistentManager persistentManager) {
		br.edu.bahiana.data.managers.PersistentManager manager = (br.edu.bahiana.data.managers.PersistentManager)persistentManager;
		manager.saveOrUpdate(object);
		
	}

}
