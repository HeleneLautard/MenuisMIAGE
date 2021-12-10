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
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import miage.toulouse.m2.helene.lautard.entities.Commande;
import miage.toulouse.m2.helene.lautard.entities.Menuiserie;
import miage.toulouse.m2.helene.lautard.facades.CommandeFacadeLocal;
import miage.toulouse.m2.helene.lautard.facades.MenuiserieFacadeLocal;
import miage.toulouse.m2.helene.lautard.sender.SenderDemandeAffaire;
import miage.toulouse.m2.helene.lautard.sender.SenderPremierChequesEncaisser;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.AffaireDTO;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CommandeNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.MenuiserieNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException;

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
    
    private SenderDemandeAffaire senderDemandeAffaire;
    
    private SenderPremierChequesEncaisser senderEncaisserCheque;
    
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
    public Commande creerCommande(String cotes, float montant, int keynumaffaire, int numMenuis, int numClient) throws MenuiserieNotFoundException {
        try {
            Menuiserie menuis = this.findMenuiserie(numMenuis);
            Commande commande = this.commandeFacade.creerCommande(cotes, montant, keynumaffaire, menuis, numClient);
            return commande;
        } catch (MenuiserieNotFoundException ex) {
            throw ex;
        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Commande findCommande(int numCommande) throws CommandeNotFoundException {
        Commande commande = this.commandeFacade.findCommandeByNum(numCommande);
        if(commande == null){
            throw new CommandeNotFoundException();
        }
        return commande;
    }

    @Override
    public void checkTotalAmount(Commande commande, double montant1, double montant2) throws WrongTotalAmountException {
        double total = commande.getMontant();
        double sum = (double) montant1 + montant2;
        if(sum != total){
            throw new WrongTotalAmountException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Commande passerCommandeFournisseur(int numCommande) throws CommandeNotFoundException, JMSException {
        try {
            Commande cmd = this.findCommande(numCommande);
            cmd.setStatut("Commandée fournisseur");
            //Demande affaire selon le numéro de la commande
            this.senderDemandeAffaire = new  SenderDemandeAffaire();
            AffaireDTO affaire = this.senderDemandeAffaire.sendDemandeAffaire(numCommande);
            // Send info pour encaissement du premier chèque de l'affaire
            this.senderEncaisserCheque.sendMsgEncaisserCheque1();
            
            return cmd;
        } catch (CommandeNotFoundException | JMSException ex){
            throw ex;
        }
    }

}
