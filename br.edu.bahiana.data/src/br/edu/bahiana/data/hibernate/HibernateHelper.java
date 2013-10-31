package br.edu.bahiana.data.hibernate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {
	private static SessionFactory sessionFactory;	  
	private static org.hibernate.service.ServiceRegistry serviceRegistry;  
	private static Map<UUID, Session> openedSessions;
	private UUID uuid;

	public HibernateHelper(){ 
		this.uuid = UUID.randomUUID();		
	}

	static {		
		Configuration configuration = new Configuration();  
		configuration.configure();  
		serviceRegistry = new org.hibernate.service.ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();          
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		openedSessions = new HashMap<UUID, Session>();
	}

	public static SessionFactory getInstance(){
		return sessionFactory;
	}

	public Session openSession() {
		System.out.println("OpenSession()");
		Session session = sessionFactory.openSession();
		openedSessions.put(this.uuid, session);
		return session;
	}
	
	public StatelessSession openStatelessSession(){
		return sessionFactory.openStatelessSession();
	}

	public Session getCurrentSession() {		
		//return sessionFactory.getCurrentSession();
		Session session = openedSessions.get(this.uuid);
		if (session == null)
			return openSession();
		return session;
	}
	
	public void closeSession(){
		Session session = getCurrentSession();
		if (session.isOpen())	{		
			session.close();
			System.out.println("CloseSession()");
			this.openedSessions.remove(this.uuid);
		}
	}


	public static void close(){
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;		
	}
}
