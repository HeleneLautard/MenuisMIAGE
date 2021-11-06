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
@Table(name = "DISPOCOMMERCIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dispocommercial.findAll", query = "SELECT d FROM Dispocommercial d")
    , @NamedQuery(name = "Dispocommercial.findByIddispocommercial", query = "SELECT d FROM Dispocommercial d WHERE d.iddispocommercial = :iddispocommercial")
    , @NamedQuery(name = "Dispocommercial.findByKeynumcommercial", query = "SELECT d FROM Dispocommercial d WHERE d.keynumcommercial = :keynumcommercial")
    , @NamedQuery(name = "Dispocommercial.findByStatut", query = "SELECT d FROM Dispocommercial d WHERE d.statut = :statut")
    , @NamedQuery(name = "Dispocommercial.findByKeynumaffaire", query = "SELECT d FROM Dispocommercial d WHERE d.keynumaffaire = :keynumaffaire")})
public class Dispocommercial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDISPOCOMMERCIAL")
    private Integer iddispocommercial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KEYNUMCOMMERCIAL")
    private int keynumcommercial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUT")
    private short statut;
    @Column(name = "KEYNUMAFFAIRE")
    private Integer keynumaffaire;
    @JoinColumn(name = "CALENDRIERIDCALENDRIER", referencedColumnName = "IDCALENDRIER")
    @OneToOne(optional = false)
    private Calendrier calendrieridcalendrier;

    public Dispocommercial() {
    }

    public Dispocommercial(Integer iddispocommercial) {
        this.iddispocommercial = iddispocommercial;
    }

    public Dispocommercial(Integer iddispocommercial, int keynumcommercial, short statut) {
        this.iddispocommercial = iddispocommercial;
        this.keynumcommercial = keynumcommercial;
        this.statut = statut;
    }

    public Integer getIddispocommercial() {
        return iddispocommercial;
    }

    public void setIddispocommercial(Integer iddispocommercial) {
        this.iddispocommercial = iddispocommercial;
    }

    public int getKeynumcommercial() {
        return keynumcommercial;
    }

    public void setKeynumcommercial(int keynumcommercial) {
        this.keynumcommercial = keynumcommercial;
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
        return "miage.toulouse.m2.helene.lautard.entities.Dispocommercial[ iddispocommercial=" + iddispocommercial + " ]";
    }
    
}
