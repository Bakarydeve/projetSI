package tests;

import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;

import donnees.Compte;
import donnees.Membre;
import eventsDAO.DAOException;
import eventsDAO.DAO_JPA;
import eventsDAO.Manager;

public class TestMembre {

	public static void main(String[] args) throws DAOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
	      EntityManager em = Manager.getEM();
	      System.out.println("PU chargée");
	      
	      DAO_JPA<Membre> daoMembre = new DAO_JPA<>(Membre.class);
	      
	      // create
	      String pseudo = "Amine";
	      Compte cpt = em.find(Compte.class, pseudo);
	      Membre membre = new Membre("Denki", "Leo", 25, "2 rue des archives", cpt);
	      /*
	      try {
			daoMembre.create(membre);
	      } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("Une erreur s'est produite lors du hachage du mot de passe : " + e.getMessage());
	      } catch (DAOException e) {
			// TODO Auto-generated catch block
			System.out.println("Une erreur s'est produite lors de la création du profil : " + e.getMessage());
	      }
	      */
	      // update
	      short id = 2;
	      membre = daoMembre.find(id);
	      membre.setAdresse("3 Jean Clemenceau");
	      //daoMembre.update(membre);
	    
	      

	}

}
