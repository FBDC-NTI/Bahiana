package br.edu.bahiana.core.datainterfaces;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface PersistentManager {	
	Object runTransaction(Method m, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
