/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.toulouse.m2.helene.lautard.entities.Menuiserie;

/**
 *
 * @author Hélène
 */
@Stateless
public class MenuiserieFacade extends AbstractFacade<Menuiserie> implements MenuiserieFacadeLocal {

    @PersistenceContext(unitName = "miage.toulouse.m2.helene.lautard_GestionAchat-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuiserieFacade() {
        super(Menuiserie.class);
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public Menuiserie creerMenuiserie(String designation, String fabricant) {
        Menuiserie menuis = new Menuiserie(designation, fabricant);
        return this.create(menuis);
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public Menuiserie findMenuiserieByNum(int numMenuiserie) {
        List<Menuiserie> res = em.createNamedQuery("Menuiserie.findByNummenuiserie")
                .setParameter("nummenuiserie", numMenuiserie)
                .getResultList();
        return res.get(0);
    }
    
}
