package testmethodes;

import java.security.NoSuchAlgorithmException;

import donnees.Membre;
import eventsDAO.DAOException;
import eventsDAO.DAO_JPA;
import methodes.Mevenement;

public class TestMevenements {

	public static void main(String[] args) throws DAOException {
		// TODO Auto-generated method stub
		Mevenement me = new Mevenement();
		
		System.out.println("tojson : " + me.AvenirtoJson());
		
		DAO_JPA<Membre> daoMembre = new DAO_JPA<>(Membre.class);
		short id = 3;
		Membre m = daoMembre.find(id);
		/*
		try {
			String pseudo = m.getPseudo().getPseudo();
			me.Inscription(pseudo, "Arcade");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
