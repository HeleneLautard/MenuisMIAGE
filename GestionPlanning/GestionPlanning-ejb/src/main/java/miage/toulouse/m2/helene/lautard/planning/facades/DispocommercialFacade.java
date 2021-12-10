/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.facades;

import java.util.List;
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

    @PersistenceContext(unitName = "miage.toulouse.m2.helene.lautard_GestionPlanning-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DispocommercialFacade() {
        super(Dispocommercial.class);
    }

    @Override
    public Dispocommercial creerDispoCommercial(int numCommercial, Calendrier creneau) {
        // Par défaut les créneaux créés sont disponibles
        Dispocommercial dispoCom = new Dispocommercial(numCommercial, creneau, true);
        return this.create(dispoCom);
    }

    @Override
    public Dispocommercial bloquerCreneauCommercial(int numCommercial, Calendrier creneau, int numAffaire, int numClient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dispocommercial> findCreneauxDispoCom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
