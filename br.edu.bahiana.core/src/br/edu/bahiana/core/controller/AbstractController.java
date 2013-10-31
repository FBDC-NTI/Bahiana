package br.edu.bahiana.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import br.edu.bahiana.core.datainterfaces.AccessDataObjectFactory;
import br.edu.bahiana.core.datainterfaces.PersistentManager;


public class AbstractController {
	private PersistentManager persistentManager;
	private static AccessDataObjectFactory accessDataObjectFactory;
	private static Lock lock = new ReentrantLock();
	

	public AbstractController(){
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
			persistentManager = accessDataObjectFactory.createPersistentManager();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public PersistentManager getPersistentManager(){
		return this.persistentManager;
	}

	public Object runUseCase(Method m, Object obj) throws IllegalAccessException, InvocationTargetException{		
		try {
			return persistentManager.runTransaction(m, obj);
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
			throw e;
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
			throw e;
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public void runUseCaseFailed(){

	}

	public void runUseCaseSuccessfully(){

	}

	public void onRunUseCase(){

	}
}
