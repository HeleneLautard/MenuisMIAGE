/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hélène
 */
@Entity
@Table(name = "POSEUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Poseur.findAll", query = "SELECT p FROM Poseur p")
    , @NamedQuery(name = "Poseur.findByNumposeur", query = "SELECT p FROM Poseur p WHERE p.numposeur = :numposeur")
    , @NamedQuery(name = "Poseur.findByNom", query = "SELECT p FROM Poseur p WHERE p.nom = :nom")
    , @NamedQuery(name = "Poseur.findByPrenom", query = "SELECT p FROM Poseur p WHERE p.prenom = :prenom")})
public class Poseur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NUMPOSEUR")
    private Integer numposeur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PRENOM")
    private String prenom;

    public Poseur() {
    }

    public Poseur(Integer numposeur) {
        this.numposeur = numposeur;
    }

    public Poseur(Integer numposeur, String nom, String prenom) {
        this.numposeur = numposeur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getNumposeur() {
        return numposeur;
    }

    public void setNumposeur(Integer numposeur) {
        this.numposeur = numposeur;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numposeur != null ? numposeur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poseur)) {
            return false;
        }
        Poseur other = (Poseur) object;
        if ((this.numposeur == null && other.numposeur != null) || (this.numposeur != null && !this.numposeur.equals(other.numposeur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.toulouse.m2.helene.lautard.entities.Poseur[ numposeur=" + numposeur + " ]";
    }
    
}
