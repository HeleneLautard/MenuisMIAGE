/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.facades;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.toulouse.m2.helene.lautard.planning.entities.Calendrier;
import miage.toulouse.m2.helene.lautard.planning.entities.Dispocommercial;

/**
 *
 * @author Hélène
 */
@Stateless
public class DispocommercialFacade extends AbstractFacade<Dispocommercial> implements DispocommercialFacadeLocal {

    @EJB
    private CalendrierFacadeLocal calendrierFacade;

    @PersistenceContext(unitName = "miage.toulouse.m2.helene.lautard_GestionPlanning-ejb_ejb_1.0PU")
    private EntityManager em;

    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DispocommercialFacade() {
        super(Dispocommercial.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispocommercial creerDispoCommercial(int numCommercial, Calendrier creneau) {
        // Par défaut les créneaux créés sont disponibles
        Dispocommercial dispoCom = new Dispocommercial(numCommercial, creneau, true);
        return this.create(dispoCom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispocommercial bloquerCreneauCommercial(Dispocommercial dispo, int numAffaire)  {
        dispo.setStatut(false);
        dispo.setNumaffaire(numAffaire);
        this.edit(dispo);
        return dispo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dispocommercial> findCreneauxDispoCom() {
        List<Dispocommercial> result = this.em.createNamedQuery("Dispocommercial.findByStatut")
                .setParameter("statut", true)
                .getResultList();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispocommercial findDispoByCommercialCreneau(int numCommercial, int numCreneau) {
        List<Dispocommercial> result = this.em.createNamedQuery("Dispocommercial.findByNumCommercialIdCalendrier")
                .setParameter("idcalendrier", numCreneau)
                .setParameter("numcommercial", numCommercial)
                .getResultList();
        return result.get(0);
    }
    
}
