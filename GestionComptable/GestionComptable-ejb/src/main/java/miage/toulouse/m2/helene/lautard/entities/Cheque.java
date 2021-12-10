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
@Table(name = "CHEQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cheque.findAll", query = "SELECT c FROM Cheque c")
    , @NamedQuery(name = "Cheque.findByNumcheque", query = "SELECT c FROM Cheque c WHERE c.numcheque = :numcheque")
    , @NamedQuery(name = "Cheque.findByMontant", query = "SELECT c FROM Cheque c WHERE c.montant = :montant")
    , @NamedQuery(name = "Cheque.findByStatut", query = "SELECT c FROM Cheque c WHERE c.statut = :statut")
    , @NamedQuery(name = "Cheque.findByRangencaissement", query = "SELECT c FROM Cheque c WHERE c.rangencaissement = :rangencaissement")
    , @NamedQuery(name = "Cheque.findByKeynumaffaire", query = "SELECT c FROM Cheque c WHERE c.keynumaffaire = :keynumaffaire")})
public class Cheque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NUMCHEQUE")
    private Integer numcheque;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTANT")
    private double montant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "STATUT")
    private String statut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "RANGENCAISSEMENT")
    private String rangencaissement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KEYNUMAFFAIRE")
    private int keynumaffaire;

    public Cheque() {
    }

    public Cheque(Integer numcheque) {
        this.numcheque = numcheque;
    }

    public Cheque(double montant, String rangencaissement, int keynumaffaire) {
        this.montant = montant;
        this.rangencaissement = rangencaissement;
        this.keynumaffaire = keynumaffaire;
    }

    public Integer getNumcheque() {
        return numcheque;
    }

    public void setNumcheque(Integer numcheque) {
        this.numcheque = numcheque;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getRangencaissement() {
        return rangencaissement;
    }

    public void setRangencaissement(String rangencaissement) {
        this.rangencaissement = rangencaissement;
    }

    public int getKeynumaffaire() {
        return keynumaffaire;
    }

    public void setKeynumaffaire(int keynumaffaire) {
        this.keynumaffaire = keynumaffaire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numcheque != null ? numcheque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cheque)) {
            return false;
        }
        Cheque other = (Cheque) object;
        if ((this.numcheque == null && other.numcheque != null) || (this.numcheque != null && !this.numcheque.equals(other.numcheque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.toulouse.m2.helene.lautard.entities.Cheque[ numcheque=" + numcheque + " ]";
    }
    
}
