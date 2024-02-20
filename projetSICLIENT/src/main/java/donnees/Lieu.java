/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package donnees;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 33751
 */
@Entity
@Table(name = "lieu")
@NamedQueries({
    @NamedQuery(name = "Lieu.findAll", query = "SELECT l FROM Lieu l"),
    @NamedQuery(name = "Lieu.findByCodeLieu", query = "SELECT l FROM Lieu l WHERE l.codeLieu = :codeLieu"),
    @NamedQuery(name = "Lieu.findByNom", query = "SELECT l FROM Lieu l WHERE l.nom = :nom"),
    @NamedQuery(name = "Lieu.findByAdresse", query = "SELECT l FROM Lieu l WHERE l.adresse = :adresse"),
    @NamedQuery(name = "Lieu.findByCapacite", query = "SELECT l FROM Lieu l WHERE l.capacite = :capacite")})
public class Lieu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_lieu")
    private Short codeLieu;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @Column(name = "capacite")
    private int capacite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeLieu")
    private List<Evenement> evenementList;

    public Lieu() {
    }

    public Lieu(Short codeLieu) {
        this.codeLieu = codeLieu;
    }

    public Lieu(Short codeLieu, String nom, String adresse, int capacite) {
        this.codeLieu = codeLieu;
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
    }
    
    public Lieu(String nom, String adresse, int capacite) {
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
    }

    public Short getCodeLieu() {
        return codeLieu;
    }

    public void setCodeLieu(Short codeLieu) {
        this.codeLieu = codeLieu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Evenement> getEvenementList() {
        return evenementList;
    }

    public void setEvenementList(List<Evenement> evenementList) {
        this.evenementList = evenementList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeLieu != null ? codeLieu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lieu)) {
            return false;
        }
        Lieu other = (Lieu) object;
        if ((this.codeLieu == null && other.codeLieu != null) || (this.codeLieu != null && !this.codeLieu.equals(other.codeLieu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "donnees.Lieu[ codeLieu=" + codeLieu + " ]";
    }
    
}
