package testmethodes;

import java.security.NoSuchAlgorithmException;

import donnees.Compte;
import eventsDAO.DAOException;
import methodes.Mcompte;

public class TestMcompte {

	public static void main(String[] args) throws DAOException {
		// TODO Auto-generated method stub
		Mcompte mc = new Mcompte();
		Compte cpt = mc.getEm().find(Compte.class, "Amine");
		
	    boolean b;
	  	try {
	  		if(cpt != null)	{
	  			b = mc.Authentification(cpt.getPseudo(), "casca");
		  		System.out.println("Ret authentification : "+b);
	  		}
	  		else {
	  			System.out.println("Pas de compte correspondant");
	  		}
	  		
	  	} catch (NoSuchAlgorithmException e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
	  	}
	  	
	  	System.out.println(mc.getMembre("aizen"));
	  	

	}
	

}
