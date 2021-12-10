/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hélène
 */
@Entity
@Table(name = "DISPOPOSEUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dispoposeur.findAll", query = "SELECT d FROM Dispoposeur d")
    , @NamedQuery(name = "Dispoposeur.findByIddispoposeur", query = "SELECT d FROM Dispoposeur d WHERE d.iddispoposeur = :iddispoposeur")
    , @NamedQuery(name = "Dispoposeur.findByNumposeur", query = "SELECT d FROM Dispoposeur d WHERE d.numposeur = :numposeur")
    , @NamedQuery(name = "Dispoposeur.findByStatut", query = "SELECT d FROM Dispoposeur d WHERE d.statut = :statut")
    , @NamedQuery(name = "Dispoposeur.findByNumaffaire", query = "SELECT d FROM Dispoposeur d WHERE d.numaffaire = :numaffaire")})
public class Dispoposeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDISPOPOSEUR")
    private Integer iddispoposeur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMPOSEUR")
    private int numposeur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUT")
    private boolean statut;
    @Column(name = "NUMAFFAIRE")
    private Integer numaffaire;
    @JoinColumn(name = "CALENDRIERIDCALENDRIER", referencedColumnName = "IDCALENDRIER")
    @ManyToOne(optional = false)
    private Calendrier calendrieridcalendrier;

    public Dispoposeur() {
    }

    
    public Dispoposeur(int numposeur, boolean statut) {
        this.numposeur = numposeur;
        this.statut = statut;
    }

    public Integer getIddispoposeur() {
        return iddispoposeur;
    }

    public void setIddispoposeur(Integer iddispoposeur) {
        this.iddispoposeur = iddispoposeur;
    }

    public int getNumposeur() {
        return numposeur;
    }

    public void setNumposeur(int numposeur) {
        this.numposeur = numposeur;
    }

    public boolean getStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public Integer getNumaffaire() {
        return numaffaire;
    }

    public void setNumaffaire(Integer numaffaire) {
        this.numaffaire = numaffaire;
    }

    public Calendrier getCalendrieridcalendrier() {
        return calendrieridcalendrier;
    }

    public void setCalendrieridcalendrier(Calendrier calendrieridcalendrier) {
        this.calendrieridcalendrier = calendrieridcalendrier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddispoposeur != null ? iddispoposeur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dispoposeur)) {
            return false;
        }
        Dispoposeur other = (Dispoposeur) object;
        if ((this.iddispoposeur == null && other.iddispoposeur != null) || (this.iddispoposeur != null && !this.iddispoposeur.equals(other.iddispoposeur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dispoposeur{" + "iddispoposeur=" + iddispoposeur + ", numposeur=" + numposeur + ", statut=" + statut + ", numaffaire=" + numaffaire + ", calendrieridcalendrier=" + calendrieridcalendrier + '}';
    }

}
