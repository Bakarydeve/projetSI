package tests;

import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;

import donnees.Compte;
import eventsDAO.DAOException;
import eventsDAO.DAO_JPA;
import eventsDAO.Manager;

public class TestCompte {

	public static void main(String[] args) throws DAOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
	      // charge le gestionnaire d'entités lié à l'unité de persistance "SportsPU"
	      EntityManager em = Manager.getEM();
	      System.out.println("PU chargée");
	      
	      DAO_JPA<Compte> daoCompte = new DAO_JPA<>(Compte.class);
	      
	      // create
	      Compte cpt = new Compte("Aimad", "etude");
	      //daoCompte.create(cpt);
	      
	      //update
	      String pseudo = "Amine";
	      cpt = em.find(Compte.class, pseudo);
	      
	      cpt.setMdp("casca");
	      //daoCompte.update(cpt);
	      

	}

}
