/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.toulouse.m2.helene.lautard.entities.Poseur;

/**
 *
 * @author Hélène
 */
@Stateless
public class PoseurFacade extends AbstractFacade<Poseur> implements PoseurFacadeLocal {

    @PersistenceContext(unitName = "miage.toulouse.m2.helene.lautard_GestionPose-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PoseurFacade() {
        super(Poseur.class);
    }
    
}
