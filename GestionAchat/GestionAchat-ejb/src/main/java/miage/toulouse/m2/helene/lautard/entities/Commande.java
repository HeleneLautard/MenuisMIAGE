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
@Table(name = "COMMANDE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
    , @NamedQuery(name = "Commande.findByNumcommande", query = "SELECT c FROM Commande c WHERE c.numcommande = :numcommande")
    , @NamedQuery(name = "Commande.findByCotes", query = "SELECT c FROM Commande c WHERE c.cotes = :cotes")
    , @NamedQuery(name = "Commande.findByMontant", query = "SELECT c FROM Commande c WHERE c.montant = :montant")
    , @NamedQuery(name = "Commande.findByKeynumaffaire", query = "SELECT c FROM Commande c WHERE c.keynumaffaire = :keynumaffaire")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NUMCOMMANDE")
    private Integer numcommande;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COTES")
    private String cotes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTANT")
    private double montant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUT")
    private String statut;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "KEYNUMAFFAIRE")
    private int keynumaffaire;
    @JoinColumn(name = "MENUISERIENUMMENUISERIE", referencedColumnName = "NUMMENUISERIE")
    @ManyToOne(optional = false)
    private Menuiserie menuiserienummenuiserie;
    @Basic(optional = false)
    @Column(name = "NUMCLIENT")
    private Integer numClient;

    public Commande() {
    }

    public Commande(Integer numcommande) {
        this.numcommande = numcommande;
    }

    public Commande(String cotes, double montant, int keynumaffaire, Menuiserie menuis, int numClient) {
        this.cotes = cotes;
        this.montant = montant;
        this.keynumaffaire = keynumaffaire;
        this.menuiserienummenuiserie = menuis;
        this.statut = "créée";
        this.numClient = numClient;
    }

    public Integer getNumcommande() {
        return numcommande;
    }

    public void setNumcommande(Integer numcommande) {
        this.numcommande = numcommande;
    }

    public Integer getNumClient() {
        return numClient;
    }

    public void setNumClient(Integer numClient) {
        this.numClient = numClient;
    }

    public String getCotes() {
        return cotes;
    }

    public void setCotes(String cotes) {
        this.cotes = cotes;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getKeynumaffaire() {
        return keynumaffaire;
    }

    public void setKeynumaffaire(int keynumaffaire) {
        this.keynumaffaire = keynumaffaire;
    }

    public Menuiserie getMenuiserienummenuiserie() {
        return menuiserienummenuiserie;
    }

    public void setMenuiserienummenuiserie(Menuiserie menuiserienummenuiserie) {
        this.menuiserienummenuiserie = menuiserienummenuiserie;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numcommande != null ? numcommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.numcommande == null && other.numcommande != null) || (this.numcommande != null && !this.numcommande.equals(other.numcommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commande{" + "numcommande=" + numcommande + ", cotes=" + cotes + ", montant=" + montant + ", statut=" + statut + ", keynumaffaire=" + keynumaffaire + ", menuiserienummenuiserie=" + menuiserienummenuiserie + '}';
    }

    
    
}
