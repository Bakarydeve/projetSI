package methodes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import donnees.Evenement;
import donnees.Membre;
import eventsDAO.DAOException;
import eventsDAO.DAO_JPA;
import eventsDAO.Manager;

public class Mevenement {
	
	private EntityManager em;
	private DAO_JPA<Evenement> daoEvenement;
	
	public Mevenement() throws DAOException	{
		this.em = Manager.getEM();
		this.daoEvenement = new DAO_JPA<>(Evenement.class);
	}
	
	public String AvenirtoJson() throws DAOException	{
		StringBuilder sb = new StringBuilder();
		List<Evenement> tmp = this.daoEvenement.findAll(Evenement.class);
		List<Evenement> evenements = new ArrayList<Evenement>();
		for(Evenement event : tmp) {
			if(event.getDateDebut().after(new Date()))	{
				evenements.add(event);
			}
		}
		
		List<Membre> membres = new ArrayList<Membre>();
		int cptevents = 0;
		
		
		sb.append("[");
		for(Evenement e : evenements)	{
			sb.append("{");
			sb.append("\"id\": " + e.getCodeEvenement() + ",");
			sb.append("\"nom\": \"" + e.getNom() + "\",");
			sb.append("\"dateDebut\": \"" + e.getDateDebut() + "\",");
			sb.append("\"dateFin\": \"" + e.getDateFin() + "\",");
			sb.append("\"capacite\": " + e.getCapacite() + ", ");
			
			
			sb.append("\"lieu\": "  + "{");
			sb.append("\"id\": " + e.getCodeLieu().getCodeLieu() + ",");
			sb.append("\"nom\": \"" + e.getCodeLieu().getNom() + "\",");
			sb.append("\"adresse\": \"" + e.getCodeLieu().getAdresse() + "\",");
			sb.append("\"capacite\": " + e.getCodeLieu().getCapacite() + "");
			sb.append("}, ");
			
			membres = e.getMembreList();
			int cptmembres = 0;
			sb.append("\"membres\":[");
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
		return sb.toString();
	}

}
