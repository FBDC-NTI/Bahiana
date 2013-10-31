package br.edu.bahiana.data.managers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.bahiana.data.hibernate.HibernateHelper;


public class PersistentManager implements br.edu.bahiana.core.datainterfaces.PersistentManager{

	private HibernateHelper hibernateHelper;
	
	public PersistentManager(){
		this.hibernateHelper = new HibernateHelper();
		this.hibernateHelper.openSession();
	}
		
	@Override
	public Object runTransaction(Method m, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {		
		Session session = hibernateHelper.getCurrentSession();
		//hibernateHelper.openSession();
		Transaction tran = null;
		try{
			tran = session.beginTransaction();
			Object returnValue = m.invoke(obj);			
			tran.commit();
			return returnValue;
		}catch(Exception ex){
			if (tran != null && !tran.wasCommitted())
				tran.rollback();
			throw ex;
		}finally{
			//hibernateHelper.closeSession();
		}
	}
	
	public Session basePersistentMethod(){
		Session session = hibernateHelper.getCurrentSession();
		if (!session.isOpen())
			session = hibernateHelper.openSession();
		return session;
	}
	
	public void save(Object transientObject){
		Session session = basePersistentMethod();
		session.save(transientObject);
	}
	
	public void update(Object persistentObject){
		Session session = basePersistentMethod();
		session.update(persistentObject);
	}
	
	public void delete(Object persistentObject){
		Session session = basePersistentMethod();
		session.delete(persistentObject);
	}
	
	public void saveOrUpdate(Object object){
		Session session = basePersistentMethod();
		session.saveOrUpdate(object);
	}
	
	public <T> List<T> getAllObjects(){
		Session session = basePersistentMethod();		
		Criteria createCriteria = session.createCriteria((Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		return createCriteria.list();
	}

	public <T> T getByID(long id, Class<T> clazz) {
		Session session = basePersistentMethod();
		Transaction transaction = session.beginTransaction();
		try{
			T object =(T) session.get((Class<T>)clazz, id);
			transaction.commit();
			return object;
		}catch(Exception ex){
			if (!transaction.wasCommitted())
				transaction.rollback();
			//if (session.isOpen())
				//session.close();
			throw ex;
		}
		
	}
}
