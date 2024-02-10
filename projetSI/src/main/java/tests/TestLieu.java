package tests;

import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;

import donnees.Lieu;
import eventsDAO.DAOException;
import eventsDAO.DAO_JPA;
import eventsDAO.Manager;

public class TestLieu {

	public static void main(String[] args) throws DAOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
	      // charge le gestionnaire d'entités lié à l'unité de persistance "SportsPU"
	      EntityManager em = Manager.getEM();
	      System.out.println("PU chargée");
	      
	      DAO_JPA<Lieu> daoLieu = new DAO_JPA<>(Lieu.class);
	      
	      // create
	      Lieu lieu = new Lieu("Arena", "140 Bd de Plymouth, 29200 Brest", 20);
	      //daoLieu.create(lieu);
	      
	      
	      // update
	      short id = 3;
	      lieu = daoLieu.find(id);
	      lieu.setCapacite(15);
	      daoLieu.update(lieu);
	      

	}

}
