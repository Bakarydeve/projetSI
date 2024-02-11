package methodes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import donnees.Evenement;
import donnees.Membre;
import eventsDAO.DAOException;
import eventsDAO.DAO_JPA;
import eventsDAO.Manager;

public class Mmembre {
	
	private EntityManager em;
	private DAO_JPA<Membre> daoMembre;
	
	public Mmembre() throws DAOException	{
		this.em = Manager.getEM();
		this.daoMembre= new DAO_JPA<>(Membre.class);
	}
	
	
	
	public EntityManager getEm() {
		return em;
	}



	public void setEm(EntityManager em) {
		this.em = em;
	}



	public DAO_JPA<Membre> getDaoMembre() {
		return daoMembre;
	}



	public void setDaoMembre(DAO_JPA<Membre> daoMembre) {
		this.daoMembre = daoMembre;
	}



	public String MembretoJson() throws DAOException	{
		StringBuilder sb = new StringBuilder();
		List<Membre> membres = this.daoMembre.findAll(Membre.class);
		List<Evenement> evenements = new ArrayList<Evenement>();
		
		int cptmembres = 0;
		
		sb.append("[");
		for(Membre m : membres)	{
			sb.append("{");
			sb.append("\"id\": " + m.getCodeMembre() + ",");
			sb.append("\"pseudo\": \"" + m.getPseudo().getPseudo() + "\",");
			sb.append("\"nom\": \"" + m.getNom() + "\",");
			sb.append("\"prenom\": \"" + m.getPrenom() + "\",");
			sb.append("\"age\": "  + m.getAge() + ",");
			sb.append("\"adresse\": \"" + m.getAdresse() + "\",");
			
			evenements = m.getEvenementList();
			int cptevents = 0;
			sb.append("\"evenements\":[");
			for(Evenement e : evenements)	{
				sb.append("{");
				sb.append("\"id\": " + e.getCodeEvenement() + ",");
				sb.append("\"nom\": \"" + e.getNom() + "\",");
				sb.append("\"dateDebut\": \"" + e.getDateDebut() + "\",");
				sb.append("\"dateFin\": \"" + e.getDateFin() + "\",");
				sb.append("\"capacite\": " + e.getCapacite() + ",");
				
	        	if(e.getPlaceDispo() == null)	{
	        		sb.append("\"placeDispo\":" + e.getPlaceDispo() +",");
	        	}
	        	else	{
	        		sb.append("\"placeDispo\":\"" + e.getPlaceDispo() + "\",");
	        	}
	        	
	        	if(e.getPlaceVendue() == null)	{
	        		sb.append("\"placeVendue\":" + e.getPlaceVendue() +",");
	        	}
	        	else	{
	        		sb.append("\"placeVendue\":\"" + e.getPlaceVendue() + "\"");
	        	}
				
				
				
				if (cptevents < evenements.size() - 1) {
	                sb.append("},");
	            }
	            else {
	            	sb.append("}");
	            }
				cptevents++;
				
			}
			sb.append("]");
			
			
			
			if (cptmembres < membres.size() - 1) {
                sb.append("},");
            }
            else {
            	sb.append("}");
            }
			
			cptmembres++;
		}
		
		sb.append("]");
		return sb.toString();
	}

}
