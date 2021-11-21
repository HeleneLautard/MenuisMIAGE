/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hélène
 */
@Entity
@Table(name = "MENUISERIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menuiserie.findAll", query = "SELECT m FROM Menuiserie m")
    , @NamedQuery(name = "Menuiserie.findByNummenuiserie", query = "SELECT m FROM Menuiserie m WHERE m.nummenuiserie = :nummenuiserie")
    , @NamedQuery(name = "Menuiserie.findByDesignation", query = "SELECT m FROM Menuiserie m WHERE m.designation = :designation")
    , @NamedQuery(name = "Menuiserie.findByFabricant", query = "SELECT m FROM Menuiserie m WHERE m.fabricant = :fabricant")})
public class Menuiserie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NUMMENUISERIE")
    private Integer nummenuiserie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESIGNATION")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FABRICANT")
    private String fabricant;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuiserienummenuiserie")
    private Collection<Commande> commandeCollection;

    public Menuiserie() {
    }

    public Menuiserie(Integer nummenuiserie) {
        this.nummenuiserie = nummenuiserie;
    }

    public Menuiserie(Integer nummenuiserie, String designation, String fabricant) {
        this.nummenuiserie = nummenuiserie;
        this.designation = designation;
        this.fabricant = fabricant;
    }

    public Integer getNummenuiserie() {
        return nummenuiserie;
    }

    public void setNummenuiserie(Integer nummenuiserie) {
        this.nummenuiserie = nummenuiserie;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    @XmlTransient
    public Collection<Commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<Commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nummenuiserie != null ? nummenuiserie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menuiserie)) {
            return false;
        }
        Menuiserie other = (Menuiserie) object;
        if ((this.nummenuiserie == null && other.nummenuiserie != null) || (this.nummenuiserie != null && !this.nummenuiserie.equals(other.nummenuiserie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "miage.toulouse.m2.helene.lautard.entities.Menuiserie[ nummenuiserie=" + nummenuiserie + " ]";
    }
    
}
