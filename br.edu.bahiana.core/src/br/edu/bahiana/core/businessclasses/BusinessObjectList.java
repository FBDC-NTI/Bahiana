package br.edu.bahiana.core.businessclasses;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import br.edu.bahiana.core.controller.AbstractController;
import br.edu.bahiana.core.datainterfaces.AccessDataObjectFactory;
import br.edu.bahiana.core.datainterfaces.AccessDataObjectList;


public class BusinessObjectList<T> {
	private AccessDataObjectList<T> implementor; 
	private static AccessDataObjectFactory accessDataObjectFactory;
	private static Lock lock = new ReentrantLock();
	private AbstractController controller;
	
	public BusinessObjectList(AbstractController controller){
		try {
			Boolean locked = false;
			if (accessDataObjectFactory == null){
				try{
					locked = lock.tryLock();
					if (locked){
						if (accessDataObjectFactory == null){
							String factoryName = "br.edu.bahiana.data.dataclasses.AccessDataObjectFactory";// really passed in from config
							Class<?> c = Class.forName(factoryName);
							accessDataObjectFactory = (AccessDataObjectFactory)c.newInstance();
						}
					}
				}finally{
					if (locked)
						lock.unlock();
				}

			}
			this.implementor = accessDataObjectFactory.createDataAcessObjectList(controller.getPersistentManager());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<T> getObjects(){		
		return this.implementor.getObjects();
	}
	
	public T getByID(long objectID, Class<T> clazz){
		return this.implementor.getObject(objectID, clazz);
	}
}
