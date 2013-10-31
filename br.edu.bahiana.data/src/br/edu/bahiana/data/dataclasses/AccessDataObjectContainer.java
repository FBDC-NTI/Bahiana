package br.edu.bahiana.data.dataclasses;

import java.util.List;

import br.edu.bahiana.core.datainterfaces.AccessDataObjectList;
import br.edu.bahiana.data.managers.PersistentManager;


public class AccessDataObjectContainer<T> implements AccessDataObjectList<T> {
	private br.edu.bahiana.core.datainterfaces.PersistentManager manager;
	
	public AccessDataObjectContainer(br.edu.bahiana.core.datainterfaces.PersistentManager manager){
		this.manager = manager;
	}
		
	public List<T> getAllObjects(){
		//manager
		return null;
	}

	@Override
	public List<T> getObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getObjects(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getObjects(int start, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getObject(long id, Class<T> clazz) {
		return ((PersistentManager)this.manager).getByID(id, clazz);		
	}
}
