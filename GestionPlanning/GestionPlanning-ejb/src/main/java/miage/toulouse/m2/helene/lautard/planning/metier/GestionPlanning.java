/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.metier;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.toulouse.m2.helene.lautard.planning.entities.Calendrier;
import miage.toulouse.m2.helene.lautard.planning.entities.Dispocommercial;
import miage.toulouse.m2.helene.lautard.planning.facades.CalendrierFacadeLocal;
import miage.toulouse.m2.helene.lautard.planning.facades.DispocommercialFacadeLocal;

/**
 *
 * @author Hélène
 */
@Stateless
public class GestionPlanning implements GestionPlanningLocal {

    @EJB
    private DispocommercialFacadeLocal dispocommercialFacade;

    @EJB
    private CalendrierFacadeLocal calendrierFacade;
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Calendrier creerCreneauCalendrier(Date dateHeureDeb, Date dateHeureFin) {
        return this.calendrierFacade.creerCreneauCalendrier(dateHeureDeb, dateHeureFin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispocommercial creerDispoCommercial(int numCommercial, Calendrier creneau) {
        return this.dispocommercialFacade.creerDispoCommercial(numCommercial, creneau);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispocommercial bloquerCreneauCommercial(int numCommercial, Calendrier creneau, int numAffaire, int numClient) {
        return this.dispocommercialFacade.bloquerCreneauCommercial(numCommercial, creneau, numAffaire, numClient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dispocommercial> findCreneauxDispoCom() {
        return this.findCreneauxDispoCom();
    }

}
