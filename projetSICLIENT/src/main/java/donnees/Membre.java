/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package donnees;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 33751
 */
@Entity
@Table(name = "membre")
@NamedQueries({
    @NamedQuery(name = "Membre.findAll", query = "SELECT m FROM Membre m"),
    @NamedQuery(name = "Membre.findByCodeMembre", query = "SELECT m FROM Membre m WHERE m.codeMembre = :codeMembre"),
    @NamedQuery(name = "Membre.findByNom", query = "SELECT m FROM Membre m WHERE m.nom = :nom"),
    @NamedQuery(name = "Membre.findByPrenom", query = "SELECT m FROM Membre m WHERE m.prenom = :prenom"),
    @NamedQuery(name = "Membre.findByAge", query = "SELECT m FROM Membre m WHERE m.age = :age"),
    @NamedQuery(name = "Membre.findByAdresse", query = "SELECT m FROM Membre m WHERE m.adresse = :adresse")})
public class Membre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code_membre")
    private Short codeMembre;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @ManyToMany(mappedBy = "membreList")
    private List<Evenement> evenementList;
    @JoinColumn(name = "pseudo", referencedColumnName = "pseudo")
    @ManyToOne(optional = false)
    private Compte pseudo;

    public Membre() {
    }

    public Membre(Short codeMembre) {
        this.codeMembre = codeMembre;
    }

    public Membre(Short codeMembre, String nom, String prenom, int age, String adresse) {
        this.codeMembre = codeMembre;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
    }
    
    public Membre(String nom, String prenom, int age, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
    }
    
    public Membre(String nom, String prenom, int age, String adresse, Compte pseudo) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.pseudo = pseudo;
    }

    public Short getCodeMembre() {
        return codeMembre;
    }

    public void setCodeMembre(Short codeMembre) {
        this.codeMembre = codeMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Evenement> getEvenementList() {
        return evenementList;
    }

    public void setEvenementList(List<Evenement> evenementList) {
        this.evenementList = evenementList;
    }

    public Compte getPseudo() {
        return pseudo;
    }

    public void setPseudo(Compte pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeMembre != null ? codeMembre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.codeMembre == null && other.codeMembre != null) || (this.codeMembre != null && !this.codeMembre.equals(other.codeMembre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "donnees.Membre[ codeMembre=" + codeMembre + " ]";
    }
    
}
