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
   
   /*
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SportsPU");
		//EntityManager entityManager = emf.createEntityManager();
        DAO_JPA<Sport> daoSport = new DAO_JPA<>(Sport.class);
        
        DAO_JPA<Sportif> daoSportif = new DAO_JPA<>(Sportif.class);
        
        DAO_JPA<Discipline> daoDisc = new DAO_JPA<>(Discipline.class);
        
        // affichage du sport 1
        Sport sport = daoSport.find(1);
        System.out.println("Le sport d'id 1 est "+sport.getIntitule() + " et ses disciplines sont :");
        for (Discipline disc : sport.getDisciplines()) {
        	System.out.println(" --> "+disc.getIntitule());
        }
        // create sport
        Sport s = new Sport();
        s.setIntitule("Danse");
        Discipline d1 = new Discipline();
        d1.setIntitule("Classique");
        d1.setSport(s);
        Discipline d2 = new Discipline();
        d2.setIntitule("Hip hop");
        d2.setSport(s);
        s.addDiscipline(d1);
        s.addDiscipline(d2);
        
        // save
        //daoSport.create(s); 
        //daoDisc.create(d1);
        //daoDisc.create(d2);
        
        // update
        Sport s1 = daoSport.find(11);
        s1.setIntitule("Danse");
        //daoSport.update(s1);
        // delete
        //Sport s2 = daoSport.find(7);
        //daoSport.delete(s2);
        
        Set<Discipline> disciplines = new HashSet<Discipline>();
        
        Sportif sportif = daoSportif.find(14);
        Discipline discipline = daoDisc.find(22);
        disciplines.add(discipline);
        
        //sportif.setDiscipline(disciplines);
        sportif.getDiscipline().add(discipline);
        //daoSportif.update(sportif);
        
        AjoutSportifDiscipline(sportif.getNom(), discipline.getIntitule());
    */
}
