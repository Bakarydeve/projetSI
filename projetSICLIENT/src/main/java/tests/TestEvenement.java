package tests;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import donnees.Evenement;
import donnees.Lieu;
import eventsDAO.DAOException;
import eventsDAO.DAO_JPA;
import eventsDAO.Manager;

public class TestEvenement {

	public static void main(String[] args) throws DAOException {
		// TODO Auto-generated method stub
	      EntityManager em = Manager.getEM();
	      System.out.println("PU chargée");
	      
	      DAO_JPA<Evenement> daoEvenement= new DAO_JPA<>(Evenement.class);
	      DAO_JPA<Lieu> daoLieu = new DAO_JPA<>(Lieu.class);
	      
	      String debut = "2024-02-21 17:30:00";
	      String fin = "2024-02-21 18:00:00";
	      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      /*
	      try {
	    	  Date dd = dateFormat.parse(debut);
	    	  Date df = dateFormat.parse(fin);
	    	  short id = 2;
		      Lieu lieu = daoLieu.find(id);
	    	  Evenement event = new Evenement("Danse", dd, df, 7, lieu);
	    	  try {
				daoEvenement.create(event);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				System.out.println("Une erreur s'est produite lors de la création de l'evenement : " + e.getMessage());
				e.printStackTrace();
			}
	      } catch (ParseException e) {
	            System.err.println("Erreur lors de l'analyse de la date : " + e.getMessage());
	            e.printStackTrace();
	        }
			*/
	      // delete
	      short id = 6;
	      Evenement event = daoEvenement.find(id);
	      if(event != null)	{
	    	  daoEvenement.delete(event);
	      }
	      
	      
	}

}
