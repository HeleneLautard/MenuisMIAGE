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
@Table(name = "DISPOCOMMERCIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dispocommercial.findAll", query = "SELECT d FROM Dispocommercial d")
    , @NamedQuery(name = "Dispocommercial.findByIddispocommercial", query = "SELECT d FROM Dispocommercial d WHERE d.iddispocommercial = :iddispocommercial")
    , @NamedQuery(name = "Dispocommercial.findByNumCommercialIdCalendrier", query = "SELECT d FROM Dispocommercial d WHERE d.numcommercial = :numcommercial AND d.calendrieridcalendrier = :idcalendrier")
    , @NamedQuery(name = "Dispocommercial.findByNumcommercial", query = "SELECT d FROM Dispocommercial d WHERE d.numcommercial = :numcommercial")
    , @NamedQuery(name = "Dispocommercial.findByStatut", query = "SELECT d FROM Dispocommercial d JOIN Calendrier c ON d.calendrieridcalendrier = c WHERE d.statut = :statut")
    , @NamedQuery(name = "Dispocommercial.findByNumaffaire", query = "SELECT d FROM Dispocommercial d WHERE d.numaffaire = :numaffaire")})
public class Dispocommercial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDISPOCOMMERCIAL")
    private Integer iddispocommercial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMCOMMERCIAL")
    private int numcommercial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUT")
    private boolean statut;
    @Column(name = "NUMAFFAIRE")
    private Integer numaffaire;
    @JoinColumn(name = "CALENDRIERIDCALENDRIER", referencedColumnName = "IDCALENDRIER")
    @ManyToOne(optional = false)
    private Calendrier calendrieridcalendrier;

    public Dispocommercial() {
    }

    
    public Dispocommercial(int numcommercial, Calendrier creneau,  boolean statut) {
        this.numcommercial = numcommercial;
        this.calendrieridcalendrier = creneau;
        this.statut = statut;
    }

    public Integer getIddispocommercial() {
        return iddispocommercial;
    }

    public void setIddispocommercial(Integer iddispocommercial) {
        this.iddispocommercial = iddispocommercial;
    }

    public int getNumcommercial() {
        return numcommercial;
    }

    public void setNumcommercial(int numcommercial) {
        this.numcommercial = numcommercial;
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
        hash += (iddispocommercial != null ? iddispocommercial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dispocommercial)) {
            return false;
        }
        Dispocommercial other = (Dispocommercial) object;
        if ((this.iddispocommercial == null && other.iddispocommercial != null) || (this.iddispocommercial != null && !this.iddispocommercial.equals(other.iddispocommercial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dispocommercial{" + "iddispocommercial=" + iddispocommercial + ", numcommercial=" + numcommercial + ", statut=" + statut + ", numaffaire=" + numaffaire + ", calendrieridcalendrier=" + calendrieridcalendrier + '}';
    }

   
    
}
