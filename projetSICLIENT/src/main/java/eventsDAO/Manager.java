package eventsDAO;

import java.sql.SQLException;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	public Manager() {
		super();
	}
	
    public static EntityManager getEM() throws DAOException {
        if (emf == null) emf = Persistence.createEntityManagerFactory("EventsPU");
		if (em == null)
		    em = emf.createEntityManager();
		return em;
    }
	
	

}
