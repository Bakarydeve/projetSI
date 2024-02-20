package eventsDAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import donnees.Compte;
import donnees.Evenement;
import donnees.Membre;

public class DAO_JPA<D> extends DAO<D> {
	
	private final Class<D> entityClass;
    private final EntityManager entityManager;
    
    private final char[] hexArray = "0123456789AGBHJK".toCharArray();
    
    public DAO_JPA(Class<D> entityClass) throws DAOException {
        this.entityClass = entityClass;
        this.entityManager = Manager.getEM();
    }
    
    public D find(Object id) {
        return entityManager.find(entityClass, id);
    }
    
    public void create(D entity) throws NoSuchAlgorithmException, DAOException {
    	
    	EntityTransaction trans = entityManager.getTransaction();
    	trans.begin();
    	
    	if (entity instanceof Compte) {
    		Compte account = (Compte) entity;
    		String algo = "SHA-256";
            
            // Vérifier si le mot de passe est non null et le hacher si nécessaire
            if (account.getMdp() != null) {
                String hashedPassword = HacheMDP(account.getMdp(), algo);
                account.setMdp(hashedPassword);
                entity = (D) account;
            }
        }
    	
    	if (entity instanceof Membre) {
    		Membre membre = (Membre) entity;
    		DAO_JPA<Membre> daoMembre = new DAO_JPA<>(Membre.class);
    		
    		List<Membre> membres = daoMembre.findAll(Membre.class);
    		for(Membre m : membres)	{
    			if(m.getNom().equals(membre.getNom()) && m.getPrenom().equals(membre.getPrenom())) {
    				throw new DAOException("Un membre porte deja le même nom et prénom");
    				
    				
    			}
    			
    		}
            entity = (D) membre;   
        }
    	
    	if (entity instanceof Evenement) {
    		
    		Evenement event = (Evenement) entity;
    	    DAO_JPA<Evenement> daoEvenement = new DAO_JPA<>(Evenement.class);
    	    List<Evenement> events = daoEvenement.findAll(Evenement.class);
    	    if (event.getDateDebut().after(event.getDateFin())) {
    	        throw new DAOException("La date de début ne peut pas être supérieure à la date de fin");
    	    }
    	    for (Evenement e : events) {
    	        if (e.getCodeEvenement() != event.getCodeEvenement() && chevauchement(e, event)) {
    	            throw new DAOException("Chevauchement : " + e.getNom() + " : "  + e.getDateDebut() + " - " + e.getDateFin() + " " + event.getNom() + " : "  + event.getDateDebut() + " - " + event.getDateFin());
    	        }
    	    }	
            entity = (D) event;   
        }
    	
        entityManager.persist(entity);
        trans.commit();
        System.out.println("Ajout OK");
    }
    
    public void update(D entity) throws NoSuchAlgorithmException {
    	EntityTransaction trans = entityManager.getTransaction();
    	trans.begin();
    	
    	if (entity instanceof Compte) {
    		Compte account = (Compte) entity;
    		String algo = "SHA-256";
            
            // Vérifier si le mot de passe est non null et le hacher si nécessaire
            if (account.getMdp() != null) {
                String hashedPassword = HacheMDP(account.getMdp(), algo);
                account.setMdp(hashedPassword);
                entity = (D) account;
            }
        }
    	
        entityManager.merge(entity);
        trans.commit();
        System.out.println("Update OK");
    }
    
    public void delete(D entity) {
    	EntityTransaction trans = entityManager.getTransaction();
    	trans.begin();
        entityManager.remove(entity);
        trans.commit();
        System.out.println("Delete OK");
    }

	@Override
	public D find(short id) throws DAOException {
		// TODO Auto-generated method stub
		return entityManager.find(entityClass, id);
	}

	@Override
	public List<D> findAll(Class<D> entityClass) throws DAOException {
	    EntityManager em = Manager.getEM();
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<D> cq = cb.createQuery(entityClass);
	    Root<D> root = cq.from(entityClass);
	    cq.select(root);
	    TypedQuery<D> query = em.createQuery(cq);
	    return query.getResultList();
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
    
    public boolean chevauchement(Evenement e1, Evenement e2) {
    	// Vérifier si les dates de début et de fin de e1 se situent à l'intérieur des dates de début et de fin de e2
        boolean debutDansE2 = (e1.getDateDebut().after(e2.getDateDebut()) || e1.getDateDebut().equals(e2.getDateDebut())) && e1.getDateDebut().before(e2.getDateFin());
        boolean finDansE2 = (e1.getDateFin().before(e2.getDateFin()) || e1.getDateFin().equals(e2.getDateFin())) && e1.getDateFin().after(e2.getDateDebut());

        // Vérifier si les dates de début et de fin de e2 se situent à l'intérieur des dates de début et de fin de e1
        boolean debutDansE1 = (e2.getDateDebut().after(e1.getDateDebut()) || e2.getDateDebut().equals(e1.getDateDebut())) && e2.getDateDebut().before(e1.getDateFin());
        boolean finDansE1 = (e2.getDateFin().before(e1.getDateFin()) || e2.getDateFin().equals(e1.getDateFin())) && e2.getDateFin().after(e1.getDateDebut());

        // Vérifier si les dates de début et de fin de e1 sont identiques à celles de e2
        boolean memesDates = e1.getDateDebut().equals(e2.getDateDebut()) && e1.getDateFin().equals(e2.getDateFin());

        // Vérifier si l'un des événements commence exactement là où l'autre se termine
        boolean debutE1FinE2 = e1.getDateDebut().equals(e2.getDateFin());
        boolean debutE2FinE1 = e2.getDateDebut().equals(e1.getDateFin());
        
        // Vérifier si la date de fin de l'un des événements est égale à la date de début de l'autre événement
        boolean finE1DebutE2 = e1.getDateFin().equals(e2.getDateDebut());
        boolean finE2DebutE1 = e2.getDateFin().equals(e1.getDateDebut());

        // Vérifier si les événements se chevauchent partiellement
        boolean chevauchementPartiel = (e1.getDateDebut().before(e2.getDateDebut()) && e1.getDateFin().after(e2.getDateDebut())) ||
                                        (e2.getDateDebut().before(e1.getDateDebut()) && e2.getDateFin().after(e1.getDateDebut()));
        
        // Vérifier si l'un des événements est inclus dans l'autre
        boolean inclusDansAutre = (e1.getDateDebut().after(e2.getDateDebut()) && e1.getDateFin().before(e2.getDateFin())) ||
                                   (e2.getDateDebut().after(e1.getDateDebut()) && e2.getDateFin().before(e1.getDateFin()));
        

        // Retourner vrai si l'une des conditions de chevauchement est satisfaite
        return debutDansE2 || finDansE2 || debutDansE1 || finDansE1 || finE1DebutE2 || finE2DebutE1 || memesDates || debutE1FinE2 || debutE2FinE1 || chevauchementPartiel || inclusDansAutre;
    }
    

}
