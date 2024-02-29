package methodes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import donnees.Evenement;
import donnees.Lieu;
import donnees.Membre;
import eventsDAO.DAOException;
import eventsDAO.DAO_JPA;
import eventsDAO.Manager;

public class Mlieu {
	
	private EntityManager em;
	private DAO_JPA<Lieu> daoLieu;
	
	public Mlieu() throws DAOException	{
		this.em = Manager.getEM();
		this.daoLieu= new DAO_JPA<>(Lieu.class);
	}
	
	public String LieutoJson() throws DAOException	{
		StringBuilder sb = new StringBuilder();
		List<Lieu> lieus = this.daoLieu.findAll(Lieu.class);
		List<Evenement> evenements = new ArrayList<Evenement>();
		List<Membre> membres = new ArrayList<Membre>();
		int cptlieu = 0;
		
		
		
		sb.append("[");
		for(Lieu l : lieus)	{
			sb.append("{");
			sb.append("\"id\": " + l.getCodeLieu() + ",");
			sb.append("\"nom\": \"" + l.getNom() + "\",");
			sb.append("\"adresse\": \"" + l.getAdresse() + "\",");
			sb.append("\"capacite\": " + l.getCapacite() + ", ");
			
			for(Evenement e : l.getEvenementList())	{
				if(e != null)	{
					if(e.getDateDebut().after(new Date()))	{
						evenements.add(e);
					}
				}
			}
			int cptevents = 0;
			sb.append("\"evenements\":[");
			for(Evenement e : evenements)	{
				sb.append("{");
				sb.append("\"id\": " + e.getCodeEvenement() + ",");
				sb.append("\"nom\": \"" + e.getNom() + "\",");
				sb.append("\"dateDebut\": \"" + e.getDateDebut() + "\",");
				sb.append("\"dateFin\": \"" + e.getDateFin() + "\",");
				sb.append("\"capacite\": " + e.getCapacite() + ", ");
				
				membres = e.getMembreList();
				int cptmembres = 0;
				sb.append("\"membres\":[");
				if(membres != null)	{
					for(Membre m : membres)	{
						sb.append("{");
						sb.append("\"id\": " + m.getCodeMembre() + ",");
						sb.append("\"pseudo\": \"" + m.getPseudo().getPseudo() + "\",");
						sb.append("\"nom\": \"" + m.getNom() + "\",");
						sb.append("\"prenom\": \"" + m.getPrenom() + "\",");
						sb.append("\"age\": "  + m.getAge() + ",");
						sb.append("\"adresse\": \"" + m.getAdresse() + "\"");			
						
						if (cptmembres < membres.size() - 1) {
			                sb.append("},");
			            }
			            else {
			            	sb.append("}");
			            }
						
						cptmembres++;
						
					}
				}
				
				sb.append("]");
				
				
				
				if (cptevents < evenements.size() - 1) {
	                sb.append("},");
	            }
	            else {
	            	sb.append("}");
	            }
				cptevents++;
			}
			sb.append("]");
			
			
			
			
			if (cptlieu < lieus.size() - 1) {
                sb.append("},");
            }
            else {
            	sb.append("}");
            }
			cptlieu++;
			evenements.clear();
			;
			
		}
		
		
		sb.append("]");
		return sb.toString();
	}

}
