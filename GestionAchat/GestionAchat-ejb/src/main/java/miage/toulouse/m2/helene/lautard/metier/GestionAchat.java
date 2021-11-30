/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.metier;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.toulouse.m2.helene.lautard.entities.Commande;
import miage.toulouse.m2.helene.lautard.entities.Menuiserie;
import miage.toulouse.m2.helene.lautard.facades.CommandeFacadeLocal;
import miage.toulouse.m2.helene.lautard.facades.MenuiserieFacadeLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.MenuiserieNotFoundException;

/**
 *
 * @author Hélène
 */
@Stateless
public class GestionAchat implements GestionAchatLocal {

    @EJB
    private MenuiserieFacadeLocal menuiserieFacade;

    @EJB
    private CommandeFacadeLocal commandeFacade;
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Menuiserie creeerMenuiserie(String designation, String fabricant) {
        return this.menuiserieFacade.creerMenuiserie(designation, fabricant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Commande creerCommande(String cotes, float montant, int keynumaffaire, int numMenuis) {
        Commande commande = new Commande();
        try {
            Menuiserie menuis = this.findMenuiserie(numMenuis);
            commande = this.commandeFacade.creerCommande(cotes, montant, keynumaffaire, menuis);
        } catch (MenuiserieNotFoundException ex) {
            System.err.println(ex);
        }
        return commande;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Menuiserie findMenuiserie(int numMenuiserie) throws MenuiserieNotFoundException {
       Menuiserie menuiserie = this.menuiserieFacade.findMenuiserieByNum(numMenuiserie);
       if(menuiserie == null){
           throw new MenuiserieNotFoundException();
       }
       return menuiserie;
    }

}
