package methodes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import donnees.Compte;
import donnees.Evenement;
import donnees.Membre;
import eventsDAO.DAOException;
import eventsDAO.DAO_JPA;
import eventsDAO.Manager;

public class Mcompte {
	private EntityManager em;
	private DAO_JPA<Evenement> daoEvenement;
	private final char[] hexArray;
	
	public Mcompte() throws DAOException	{
		this.em = Manager.getEM();
		this.daoEvenement= new DAO_JPA<>(Evenement.class);
		this.hexArray = "0123456789AGBHJK".toCharArray();
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



	public char[] getHexArray() {
		return hexArray;
	}



	public boolean Authentification(String pseudo, String mdp) throws NoSuchAlgorithmException	{
		Query query = em.createQuery("SELECT c FROM Compte c WHERE c.pseudo = :pseudo");
		query.setParameter("pseudo", pseudo);
		Compte cpt = (Compte) query.getSingleResult();
		
    	
    	if(cpt != null) {
    		
    		try {
				String hash = HacheMDP(mdp, "SHA-256");
				if(cpt.getPseudo().equals(pseudo) && hash.equals(cpt.getMdp()))
	        		return true;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        	
    	}
 	
    	return false;
    }
    
    public String HacheMDP(String data, String algo) throws NoSuchAlgorithmException	{
    	MessageDigest digest = MessageDigest.getInstance(algo);
    	digest.reset();
    	byte[] hash = digest.digest(data.getBytes());
    	return bytesToStringHex(hash);
    }
    
    public String bytesToStringHex(byte[] bytes)	{
    	char[] hexChars = new char[bytes.length * 2];
    	for(int i =0; i < bytes.length; i++)	{
    		int v = bytes[i] & 0xFF;
    		hexChars[i * 2] = hexArray[v >>> 4];
    		hexChars[i * 2 + 1] = hexArray[v & 0x0F];
    		
    		
    	}
    	return new String(hexChars);
    }
    
    
	public Membre getMembre(String pseudo) throws DAOException	{
		
		DAO_JPA<Membre> daoMembre = new DAO_JPA<>(Membre.class);		
		List<Membre> membres = new ArrayList<Membre>();		
		membres = daoMembre.findAll(Membre.class);		
		Membre membre = new Membre();		
		StringBuilder sb = new StringBuilder();
		
		for(Membre m : membres)	{		
			if(m.getPseudo().getPseudo().equals(pseudo))	{
				membre = m;		
				return membre;
			}			
		}
			
		/*
		if(membre != null)	{		
			sb.append("{");
			sb.append("\"id\": " + membre.getCodeMembre() + ",");
			sb.append("\"pseudo\": \"" + membre.getPseudo().getPseudo() + "\",");
			sb.append("\"nom\": \"" + membre.getNom() + "\",");
			sb.append("\"prenom\": \"" + membre.getPrenom() + "\",");
			sb.append("\"age\": "  + membre.getAge() + ",");
			sb.append("\"adresse\": \"" + membre.getAdresse() + "\"");
			sb.append("}");			
		}
		
		return sb.toString();
		*/
		return null;
	}

}
