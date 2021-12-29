/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Hélène
 */
public class PlanningCommercialDTO implements Serializable {
    
    int idCommercial;
    Date dateHeureDeb;
    Date dateHeureFin;
    boolean dispo;
    int numAffaire;

    public PlanningCommercialDTO(int idCommercial, Date dateHeureDeb, Date dateHeureFin, boolean dispo) {
        this.idCommercial = idCommercial;
        this.dateHeureDeb = dateHeureDeb;
        this.dateHeureFin = dateHeureFin;
        this.dispo = dispo;
    }

    public int getIdCommercial() {
        return idCommercial;
    }

    public Date getDateHeureDeb() {
        return dateHeureDeb;
    }

    public Date getDateHeureFin() {
        return dateHeureFin;
    }

    public boolean isDispo() {
        return dispo;
    }

    public int getNumAffaire() {
        return numAffaire;
    }

    @Override
    public String toString() {
        return "PlanningCommercialDTO{" + "idCommercial=" + idCommercial + ", dateHeureDeb=" + dateHeureDeb + ", dateHeureFin=" + dateHeureFin + ", dispo=" + dispo + ", numAffaire=" + numAffaire + '}';
    }
    
    
}
