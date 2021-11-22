/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.toulouse.m2.helene.lautard.entities.Commercial;

/**
 *
 * @author Hélène
 */
@Stateless
public class CommercialFacade extends AbstractFacade<Commercial> implements CommercialFacadeLocal {

    @PersistenceContext(unitName = "miage.toulouse.m2.eai_GestionAffaire-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommercialFacade() {
        super(Commercial.class);
    }
    
}
