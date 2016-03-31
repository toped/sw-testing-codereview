package org.msstate.cse.changecalculator.hibernate;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	
	private static SessionFactory factory = null;
	
	
	private static SessionFactory getSessionFactory (){
		if(factory == null){
			try {
				BasicConfigurator.configure();
				//create factory
			    Configuration cfg=new Configuration();  //creating configuration object  
			    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file 
			    //creating seession factory object  
			   factory=cfg.buildSessionFactory();
			} catch (Exception e) {
				System.err.println("Hibernate Configuration Error");
				e.printStackTrace();
			}
		   return factory;
		}else{
			return factory;
		}
	}
	
	public static Session getSession(){
	      //factory
		SessionFactory factory = getSessionFactory();
	    //creating session object  
	    Session session=factory.openSession();  
	      
	    return session;
	}

}
