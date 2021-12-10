/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hélène
 */
@Entity
@Table(name = "CALENDRIER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calendrier.findAll", query = "SELECT c FROM Calendrier c")
    , @NamedQuery(name = "Calendrier.findByIdcalendrier", query = "SELECT c FROM Calendrier c WHERE c.idcalendrier = :idcalendrier")
    , @NamedQuery(name = "Calendrier.findByDateheuredeb", query = "SELECT c FROM Calendrier c WHERE c.dateheuredeb = :dateheuredeb")
    , @NamedQuery(name = "Calendrier.findByDateheurefin", query = "SELECT c FROM Calendrier c WHERE c.dateheurefin = :dateheurefin")})
public class Calendrier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCALENDRIER")
    private Integer idcalendrier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEHEUREDEB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateheuredeb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEHEUREFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateheurefin;
    
    
    public Calendrier() {
    }

    
    public Calendrier(Date dateheuredeb, Date dateheurefin) {
        this.dateheuredeb = dateheuredeb;
        this.dateheurefin = dateheurefin;
    }

    public Integer getIdcalendrier() {
        return idcalendrier;
    }

    public void setIdcalendrier(Integer idcalendrier) {
        this.idcalendrier = idcalendrier;
    }

    public Date getDateheuredeb() {
        return dateheuredeb;
    }

    public void setDateheuredeb(Date dateheuredeb) {
        this.dateheuredeb = dateheuredeb;
    }

    public Date getDateheurefin() {
        return dateheurefin;
    }

    public void setDateheurefin(Date dateheurefin) {
        this.dateheurefin = dateheurefin;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcalendrier != null ? idcalendrier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendrier)) {
            return false;
        }
        Calendrier other = (Calendrier) object;
        if ((this.idcalendrier == null && other.idcalendrier != null) || (this.idcalendrier != null && !this.idcalendrier.equals(other.idcalendrier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Calendrier{" + "idcalendrier=" + idcalendrier + ", dateheuredeb=" + dateheuredeb + ", dateheurefin=" + dateheurefin + '}';
    }

    

}
