/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package donnees;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 33751
 */
@Entity
@Table(name = "evenement")
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e"),
    @NamedQuery(name = "Evenement.findByCodeEvenement", query = "SELECT e FROM Evenement e WHERE e.codeEvenement = :codeEvenement"),
    @NamedQuery(name = "Evenement.findByNom", query = "SELECT e FROM Evenement e WHERE e.nom = :nom"),
    @NamedQuery(name = "Evenement.findByDateDebut", query = "SELECT e FROM Evenement e WHERE e.dateDebut = :dateDebut"),
    @NamedQuery(name = "Evenement.findByDateFin", query = "SELECT e FROM Evenement e WHERE e.dateFin = :dateFin"),
    @NamedQuery(name = "Evenement.findByCapacite", query = "SELECT e FROM Evenement e WHERE e.capacite = :capacite"),
    @NamedQuery(name = "Evenement.findByPlaceDispo", query = "SELECT e FROM Evenement e WHERE e.placeDispo = :placeDispo"),
    @NamedQuery(name = "Evenement.findByPlaceVendue", query = "SELECT e FROM Evenement e WHERE e.placeVendue = :placeVendue")})
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_evenement")
    private Short codeEvenement;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "date_debut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "date_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Basic(optional = false)
    @Column(name = "capacite")
    private int capacite;
    @Column(name = "place_dispo")
    private Integer placeDispo;
    @Column(name = "place_vendue")
    private Integer placeVendue;
    @JoinTable(name = "inscription", joinColumns = {
        @JoinColumn(name = "code_evenement", referencedColumnName = "code_evenement")}, inverseJoinColumns = {
        @JoinColumn(name = "code_membre", referencedColumnName = "code_membre")})
    @ManyToMany
    private List<Membre> membreList;
    @JoinColumn(name = "code_lieu", referencedColumnName = "code_lieu")
    @ManyToOne(optional = false)
    private Lieu codeLieu;

    public Evenement() {
    }

    public Evenement(Short codeEvenement) {
        this.codeEvenement = codeEvenement;
    }

    public Evenement(Short codeEvenement, String nom, Date dateDebut, Date dateFin, int capacite) {
        this.codeEvenement = codeEvenement;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.capacite = capacite;
    }
    
    public Evenement(String nom, Date dateDebut, Date dateFin, int capacite) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.capacite = capacite;
    }
    
    public Evenement(String nom, Date dateDebut, Date dateFin, int capacite, Lieu codeLieu) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.capacite = capacite;
        this.codeLieu = codeLieu;
    }

    public Short getCodeEvenement() {
        return codeEvenement;
    }

    public void setCodeEvenement(Short codeEvenement) {
        this.codeEvenement = codeEvenement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Integer getPlaceDispo() {
        return placeDispo;
    }

    public void setPlaceDispo(Integer placeDispo) {
        this.placeDispo = placeDispo;
    }

    public Integer getPlaceVendue() {
        return placeVendue;
    }

    public void setPlaceVendue(Integer placeVendue) {
        this.placeVendue = placeVendue;
    }

    public List<Membre> getMembreList() {
        return membreList;
    }

    public void setMembreList(List<Membre> membreList) {
        this.membreList = membreList;
    }

    public Lieu getCodeLieu() {
        return codeLieu;
    }

    public void setCodeLieu(Lieu codeLieu) {
        this.codeLieu = codeLieu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeEvenement != null ? codeEvenement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.codeEvenement == null && other.codeEvenement != null) || (this.codeEvenement != null && !this.codeEvenement.equals(other.codeEvenement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "donnees.Evenement[ codeEvenement=" + codeEvenement + " ]";
    }
    
}
