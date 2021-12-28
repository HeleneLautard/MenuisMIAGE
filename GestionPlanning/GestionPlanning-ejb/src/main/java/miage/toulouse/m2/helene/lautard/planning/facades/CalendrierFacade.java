/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.facades;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.toulouse.m2.helene.lautard.planning.entities.Calendrier;

/**
 *
 * @author Hélène
 */
@Stateless
public class CalendrierFacade extends AbstractFacade<Calendrier> implements CalendrierFacadeLocal {

    @PersistenceContext(unitName = "miage.toulouse.m2.helene.lautard_GestionPlanning-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalendrierFacade() {
        super(Calendrier.class);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Calendrier creerCreneauCalendrier(Date dateHeureDeb, Date dateHeureFin) {
        Calendrier calendrier = new Calendrier(dateHeureDeb, dateHeureFin);
        return this.create(calendrier);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Calendrier findCreneauByNum(int numCreneau) {
        List<Calendrier> res = this.em.createNamedQuery("Calendrier.findByIdcalendrier")
                .setParameter("idcalendrier", numCreneau)
                .getResultList();
        return res.get(0);
    }
    
}
