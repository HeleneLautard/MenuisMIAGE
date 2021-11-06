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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
    , @NamedQuery(name = "Dispoposeur.findByKeynumposeur", query = "SELECT d FROM Dispoposeur d WHERE d.keynumposeur = :keynumposeur")
    , @NamedQuery(name = "Dispoposeur.findByStatut", query = "SELECT d FROM Dispoposeur d WHERE d.statut = :statut")
    , @NamedQuery(name = "Dispoposeur.findByKeynumaffaire", query = "SELECT d FROM Dispoposeur d WHERE d.keynumaffaire = :keynumaffaire")})
public class Dispoposeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDISPOPOSEUR")
    private Integer iddispoposeur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KEYNUMPOSEUR")
    private int keynumposeur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUT")
    private short statut;
    @Column(name = "KEYNUMAFFAIRE")
    private Integer keynumaffaire;
    @JoinColumn(name = "CALENDRIERIDCALENDRIER", referencedColumnName = "IDCALENDRIER")
    @OneToOne(optional = false)
    private Calendrier calendrieridcalendrier;

    public Dispoposeur() {
    }

    public Dispoposeur(Integer iddispoposeur) {
        this.iddispoposeur = iddispoposeur;
    }

    public Dispoposeur(Integer iddispoposeur, int keynumposeur, short statut) {
        this.iddispoposeur = iddispoposeur;
        this.keynumposeur = keynumposeur;
        this.statut = statut;
    }

    public Integer getIddispoposeur() {
        return iddispoposeur;
    }

    public void setIddispoposeur(Integer iddispoposeur) {
        this.iddispoposeur = iddispoposeur;
    }

    public int getKeynumposeur() {
        return keynumposeur;
    }

    public void setKeynumposeur(int keynumposeur) {
        this.keynumposeur = keynumposeur;
    }

    public short getStatut() {
        return statut;
    }

    public void setStatut(short statut) {
        this.statut = statut;
    }

    public Integer getKeynumaffaire() {
        return keynumaffaire;
    }

    public void setKeynumaffaire(Integer keynumaffaire) {
        this.keynumaffaire = keynumaffaire;
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
        return "miage.toulouse.m2.helene.lautard.entities.Dispoposeur[ iddispoposeur=" + iddispoposeur + " ]";
    }
    
}
