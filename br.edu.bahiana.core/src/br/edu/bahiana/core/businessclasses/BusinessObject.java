package br.edu.bahiana.core.businessclasses;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import br.edu.bahiana.core.controller.AbstractController;
import br.edu.bahiana.core.datainterfaces.AccessDataObject;
import br.edu.bahiana.core.datainterfaces.AccessDataObjectFactory;

public class BusinessObject {
	private AccessDataObject implementor;
	private static AccessDataObjectFactory accessDataObjectFactory;
	private static Lock lock = new ReentrantLock();
	private AbstractController controller;
	protected long ID;
	
		
	public BusinessObject(){
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
			this.implementor = accessDataObjectFactory.createDataAcessObject();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(){		
		if (controller == null)
			throw new IllegalStateException("Controller must be defined.");
		this.implementor.save(this, controller.getPersistentManager());
	}
	public void update(){
		if (controller == null)
			throw new IllegalStateException("Controller must be defined.");
		this.implementor.update(this, controller.getPersistentManager());
	}
	public void delete(){
		if (controller == null)
			throw new IllegalStateException("Controller must be defined.");
		this.implementor.delete(this, controller.getPersistentManager());
	}
	public long getID() {
		return this.ID;
	}
	public void setID(long id) {
		ID = id;
	}	
	public AbstractController getController(){
		return this.controller;
	}
	public void setController(AbstractController controller){
		this.controller = controller;
	}
}
