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
import javax.persistence.ManyToOne;
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
@Table(name = "AFFAIRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Affaire.findAll", query = "SELECT a FROM Affaire a")
    , @NamedQuery(name = "Affaire.findByNumaffaire", query = "SELECT a FROM Affaire a WHERE a.numaffaire = :numaffaire")
    , @NamedQuery(name = "Affaire.findByLieupose", query = "SELECT a FROM Affaire a WHERE a.lieupose = :lieupose")
    , @NamedQuery(name = "Affaire.findByStatut", query = "SELECT a FROM Affaire a WHERE a.statut = :statut")
    , @NamedQuery(name = "Affaire.findByKeynumcommercial", query = "SELECT a FROM Affaire a WHERE a.keynumcommercial = :keynumcommercial")
    , @NamedQuery(name = "Affaire.findByKeynumcommande", query = "SELECT a FROM Affaire a WHERE a.keynumcommande = :keynumcommande")
    , @NamedQuery(name = "Affaire.findByKeynumposeur", query = "SELECT a FROM Affaire a WHERE a.keynumposeur = :keynumposeur")})
public class Affaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NUMAFFAIRE")
    private Integer numaffaire;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LIEUPOSE")
    private String lieupose;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "STATUT")
    private String statut;
    @Column(name = "KEYNUMCOMMERCIAL")
    private Integer keynumcommercial;
    @Column(name = "KEYNUMCOMMANDE")
    private Integer keynumcommande;
    @Column(name = "KEYNUMPOSEUR")
    private Integer keynumposeur;
    @JoinColumn(name = "CLIENTNUMCLIENT", referencedColumnName = "NUMCLIENT")
    @ManyToOne(optional = false)
    private Client clientnumclient;

    public Affaire() {
    }

    public Affaire(Integer numaffaire) {
        this.numaffaire = numaffaire;
    }

    public Affaire(Integer numaffaire, String lieupose, String statut) {
        this.numaffaire = numaffaire;
        this.lieupose = lieupose;
        this.statut = statut;
    }

    public Integer getNumaffaire() {
        return numaffaire;
    }

    public void setNumaffaire(Integer numaffaire) {
        this.numaffaire = numaffaire;
    }

    public String getLieupose() {
        return lieupose;
    }

    public void setLieupose(String lieupose) {
        this.lieupose = lieupose;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getKeynumcommercial() {
        return keynumcommercial;
    }

    public void setKeynumcommercial(Integer keynumcommercial) {
        this.keynumcommercial = keynumcommercial;
    }

    public Integer getKeynumcommande() {
        return keynumcommande;
    }

    public void setKeynumcommande(Integer keynumcommande) {
        this.keynumcommande = keynumcommande;
    }

    public Integer getKeynumposeur() {
        return keynumposeur;
    }

    public void setKeynumposeur(Integer keynumposeur) {
        this.keynumposeur = keynumposeur;
    }

    public Client getClientnumclient() {
        return clientnumclient;
    }

    public void setClientnumclient(Client clientnumclient) {
        this.clientnumclient = clientnumclient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numaffaire != null ? numaffaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Affaire)) {
            return false;
        }
        Affaire other = (Affaire) object;
        if ((this.numaffaire == null && other.numaffaire != null) || (this.numaffaire != null && !this.numaffaire.equals(other.numaffaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.toulouse.m2.helene.lautard.entities.Affaire[ numaffaire=" + numaffaire + " ]";
    }
    
}
