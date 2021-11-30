/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.toulouse.m2.helene.lautard.entities.Commande;
import miage.toulouse.m2.helene.lautard.entities.Menuiserie;

/**
 *
 * @author Hélène
 */
@Stateless
public class CommandeFacade extends AbstractFacade<Commande> implements CommandeFacadeLocal {

    @PersistenceContext(unitName = "miage.toulouse.m2.helene.lautard_GestionAchat-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeFacade() {
        super(Commande.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Commande creerCommande(String cotes, float montant, int keynumaffaire, Menuiserie menuis) {
        Commande commande = new Commande(cotes, montant, keynumaffaire, menuis);
        return this.create(commande);
    }
    
}
