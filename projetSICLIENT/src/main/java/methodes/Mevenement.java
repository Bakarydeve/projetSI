package methodes;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public DAO_JPA<Evenement> getDaoEvenement() {
		return daoEvenement;
	}

	public void setDaoEvenement(DAO_JPA<Evenement> daoEvenement) {
		this.daoEvenement = daoEvenement;
	}
	
	public void Inscription(String pseudomembre, String nomevent) throws DAOException, NoSuchAlgorithmException {
		// requête JPQL pour récupérer les sportifs dans la BDD
		EntityManager em = Manager.getEM();
		DAO_JPA<Evenement> daoEvenement = new DAO_JPA<>(Evenement.class);
		DAO_JPA<Membre> daoMembre = new DAO_JPA<>(Membre.class);
		//EntityTransaction trans = em.getTransaction();
		
		List<Membre> lm = daoMembre.findAll(Membre.class);
		Membre membre = new Membre();
		short id = -1;
		
		for(Membre m : lm)	{
			if(m.getPseudo() != null)	{
				if(m.getPseudo().getPseudo().equals(pseudomembre))	{
					id = m.getCodeMembre();
					break;
				}
			}
			
		}
		
		membre = daoMembre.find(id);
		
		Query requete = em.createQuery("SELECT e FROM Evenement e WHERE e.nom = :nom");
		requete.setParameter("nom", nomevent);		
		Evenement event = (Evenement) requete.getSingleResult();
		
		if(event.getMembreList().contains(membre))	{
			throw new DAOException("Vous êtes déjà inscrit à cet evenement");
		}
		
		if(membre != null && event != null)	{
			event.getMembreList().add(membre);
			membre.getEvenementList().add(event);
			
			int dispo = event.getPlaceDispo() - 1;
			int vendu = event.getPlaceVendue() + 1;
			event.setPlaceDispo(dispo);
			event.setPlaceVendue(vendu);
			
			daoEvenement.update(event);
			daoMembre.update(membre);
		}
		

	}
	
	public void Desinscription(String pseudomembre, String nomevent) throws DAOException, NoSuchAlgorithmException {
		// requête JPQL pour récupérer les sportifs dans la BDD
		EntityManager em = Manager.getEM();
		DAO_JPA<Evenement> daoEvenement = new DAO_JPA<>(Evenement.class);
		DAO_JPA<Membre> daoMembre = new DAO_JPA<>(Membre.class);
		//EntityTransaction trans = em.getTransaction();
		
		List<Membre> lm = daoMembre.findAll(Membre.class);
		Membre membre = new Membre();
		short id = -1;
		
		for(Membre m : lm)	{
			if(m.getPseudo() != null)	{
				if(m.getPseudo().getPseudo().equals(pseudomembre))	{
					id = m.getCodeMembre();
					break;
				}
			}
			
		}
		
		membre = daoMembre.find(id);
		
		Query requete = em.createQuery("SELECT e FROM Evenement e WHERE e.nom = :nom");
		requete.setParameter("nom", nomevent);		
		Evenement event = (Evenement) requete.getSingleResult();
		
		if(!(event.getMembreList().contains(membre)))	{
			throw new DAOException("Vous n'êtes pas inscrit à cet evenement");
		}
		if(membre != null && event != null)	{
			event.getMembreList().remove(membre);
			membre.getEvenementList().remove(event);
			
			int dispo = event.getPlaceDispo() + 1;
			int vendu = event.getPlaceVendue() - 1;
			event.setPlaceDispo(dispo);
			event.setPlaceVendue(vendu);
			
			daoEvenement.update(event);
			daoMembre.update(membre);
			return;
		}
		
			
		
		

	}

	public String AvenirtoJson() throws DAOException	{
		StringBuilder sb = new StringBuilder();
		List<Evenement> tmp = this.daoEvenement.findAll(Evenement.class);
		List<Evenement> evenements = new ArrayList<Evenement>();
		for(Evenement event : tmp) {
			if( event.getDateDebut() != null)	{
				if(event.getDateDebut().after(new Date()))	{
					evenements.add(event);
				}
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
			sb.append("\"dispo\": " + e.getPlaceDispo() + ",");
			sb.append("\"vendue\": " + e.getPlaceVendue() + ",");
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
				if(m.getPseudo() == null)	{
					sb.append("\"pseudo\": \"" + m.getPseudo() + "\",");
				}
				else {
					sb.append("\"pseudo\": \"" + m.getPseudo().getPseudo() + "\",");
				}		
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
