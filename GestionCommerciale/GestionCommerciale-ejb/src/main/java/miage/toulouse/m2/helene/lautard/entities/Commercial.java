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
@Table(name = "COMMERCIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commercial.findAll", query = "SELECT c FROM Commercial c")
    , @NamedQuery(name = "Commercial.findByNumcommercial", query = "SELECT c FROM Commercial c WHERE c.numcommercial = :numcommercial")
    , @NamedQuery(name = "Commercial.findByNom", query = "SELECT c FROM Commercial c WHERE c.nom = :nom")
    , @NamedQuery(name = "Commercial.findByPrenom", query = "SELECT c FROM Commercial c WHERE c.prenom = :prenom")})
public class Commercial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NUMCOMMERCIAL")
    private Integer numcommercial;
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

    public Commercial() {
    }

    public Commercial(Integer numcommercial) {
        this.numcommercial = numcommercial;
    }

    public Commercial(Integer numcommercial, String nom, String prenom) {
        this.numcommercial = numcommercial;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getNumcommercial() {
        return numcommercial;
    }

    public void setNumcommercial(Integer numcommercial) {
        this.numcommercial = numcommercial;
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
        hash += (numcommercial != null ? numcommercial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commercial)) {
            return false;
        }
        Commercial other = (Commercial) object;
        if ((this.numcommercial == null && other.numcommercial != null) || (this.numcommercial != null && !this.numcommercial.equals(other.numcommercial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.toulouse.m2.helene.lautard.entities.Commercial[ numcommercial=" + numcommercial + " ]";
    }
    
}
