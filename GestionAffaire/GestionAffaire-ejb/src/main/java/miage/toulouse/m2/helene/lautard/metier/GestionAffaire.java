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
import miage.toulouse.m2.helene.lautard.entities.Affaire;
import miage.toulouse.m2.helene.lautard.entities.Client;
import miage.toulouse.m2.helene.lautard.facades.AffaireFacadeLocal;
import miage.toulouse.m2.helene.lautard.facades.ClientFacadeLocal;
import miage.toulouse.m2.helene.lautard.sender.SenderAffaires;
import miage.toulouse.m2.helene.lautard.sender.SenderCommandeAchat;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongClientException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException;

/**
 *
 * @author Hélène
 */
@Stateless
public class GestionAffaire implements GestionAffaireLocal {

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private AffaireFacadeLocal affaireFacade;
    
    private SenderCommandeAchat senderCommande;
    
    private SenderAffaires senderAffaire;
    

    /**
     * {@inheritDoc}
     */
    @Override
    public Affaire creerAffaire(int numclient, String lieuPose) throws ClientNotFoundException {
        try {
            Client clt = this.findClient(numclient);
            Affaire affaire = this.affaireFacade.creerAffaire(clt, lieuPose);
            return affaire;
        } catch (ClientNotFoundException ex) {
            throw(ex);
        }
    }


    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Client creerClient(String nom, String prenom, String mail, String adresseP, String telephone) {
        return this.clientFacade.creerClient(nom, prenom, mail, adresseP, telephone);
    }

    
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Affaire renseignerCommande(int numAffaire, int numClient, int numMenuiserie, String cotes, float montant) throws AffaireNotFoundException, WrongClientException, JMSException {
       try {            
            Affaire aff = this.findAffaire(numAffaire);
            aff = this.checkClient(numAffaire, numClient);
            // Création du senderCommande avec l'affaire à mettre à jour
            this.senderCommande = new SenderCommandeAchat(aff, numClient);
            //Demande de création de la commande auprès du service achat
            this.senderCommande.sendDemandeCommande(cotes, montant, aff.getNumaffaire(), numMenuiserie, numClient);
            return this.findAffaire(numAffaire);
        } catch (AffaireNotFoundException ex) {
           throw ex;
        } 
    }

    
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Affaire findAffaire(int numAffaire) throws AffaireNotFoundException {
        Affaire affaire = this.affaireFacade.findAffaireByNum(numAffaire);
        if(affaire == null){
            throw new AffaireNotFoundException();
        }
        return affaire;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Client findClient(int numClient) throws ClientNotFoundException {
        Client client = this.clientFacade.findClientByNum(numClient);
        if (client == null) {
            throw new ClientNotFoundException();
        }
        return client;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Affaire checkClient(int numAffaire, int numClient) throws WrongClientException {
        Affaire affaire = this.affaireFacade.checkClientAffaire(numAffaire, numClient);
        if(affaire == null){
            throw new WrongClientException();
        }
        return affaire;
    }

    @Override
    public void validerCommande(int numAffaire, float montant1, float montant2) throws AffaireNotFoundException, WrongTotalAmountException {
        // Récupérer l'affaire
        Affaire aff = this.findAffaire(numAffaire);
        //MAJ de l'entité affaire 
        this.affaireFacade.validerCommande(aff);
        // La marquer comme "commande validée" dans le topic pour les autres gestion
        this.senderAffaire = new SenderAffaires();
        this.senderAffaire.sendMsgCommandeValidée(aff);
    }

    
}
