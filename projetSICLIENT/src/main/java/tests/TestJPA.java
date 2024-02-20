package tests;

import javax.persistence.EntityManager;

import donnees.Lieu;
import eventsDAO.Manager;

public class TestJPA {

   public static void main(String argv[]) throws Exception {

      // charge le gestionnaire d'entités lié à l'unité de persistance "SportsPU"
      EntityManager em = Manager.getEM();
      System.out.println("PU chargée");

      // récupère le sport d'identifiant 1, affiche son intitulé et ses disciplines
      short cle = 1;
      Lieu lieu = em.find(Lieu.class, cle);
      System.out.println("Le lieu " + cle + " est " + lieu.getNom());
   }
   
   
}
