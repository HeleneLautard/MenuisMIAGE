/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.toulouse.m2.helene.lautard.entities.Cheque;

/**
 *
 * @author Hélène
 */
@Stateless
public class ChequeFacade extends AbstractFacade<Cheque> implements ChequeFacadeLocal {

    @PersistenceContext(unitName = "miage.toulouse.m2.helene.lautard_GestionComptable-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChequeFacade() {
        super(Cheque.class);
    }

    @Override
    public Cheque creerCheque(float montant, String rangencaissement, int keynumaffaire) {
        Cheque cheque = new Cheque(montant, rangencaissement, keynumaffaire);
        return this.create(cheque);
    }
    
}
