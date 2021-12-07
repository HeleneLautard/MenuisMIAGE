/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.metier;

import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.entities.Affaire;
import miage.toulouse.m2.helene.lautard.entities.Client;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongClientException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException;

/**
 *
 * @author Hélène
 */
@Local
public interface GestionAffaireLocal {
    
    /**
     * Création d'une affaire au début du processus de gestion
     * @param numclient Client rattaché à l'affaire
     * @param lieuPose Lieu d'installation de la menuiserie
     * @return Affaire créée
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException
     */
    Affaire creerAffaire(int numclient, String lieuPose) throws ClientNotFoundException;
        
    /**
     * Recherche d'un client
     * @param numClient numéro d'identification d'un client
     * @return Client trouvé
     * @throws ClientNotFoundException
     */
    Client findClient(int numClient) throws ClientNotFoundException;
    
    /**
     * Création d'un client
     * @param nom nom du client
     * @param prenom prenom du client
     * @param mail adresse mail du client
     * @param adresseP adresse postale du client
     * @param telephone numéro de téléphone du client
     * @return Client
     */
    Client creerClient(String nom, String prenom, String mail, String adresseP, String telephone);
    
    /**
     * Vérifier l'existance d'une affaire
     * @param numAffaire numéro de l'affaire recherchée
     * @return Affaire trouvée
     * @throws AffaireNotFoundException 
     */
    Affaire findAffaire(int numAffaire) throws AffaireNotFoundException;
    
    /**
     * Vérification de la bonne combinaison client/affaire
     * @param numAffaire numéro de l'affaire
     * @param numClient numéro du client
     * @return Affaire 
     * @throws WrongClientException 
     */
    Affaire checkClient(int numAffaire, int numClient) throws WrongClientException;
    
    /**
     * Renseigner une commande pour une affaire
     * @param numAffaire numéro de l'affaire concernée
     * @param numClient numéro du client associé à l'affaire
     * @param numMenuiserie numéro de la menuiserie à commander
     * @param cotes cotes de la menuiserie
     * @param montant montant négocié
     * @return Affaire mise à jour
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongClientException
     */
    Affaire renseignerCommande(int numAffaire, int numClient, int numMenuiserie, String cotes, float montant) throws AffaireNotFoundException, WrongClientException;
    
    
    /**
     * Valider une commande pour une affaire
     * @param numAffaire numéro de l'affaire concernée
     * @param montant1 montant du chèque N°1
     * @param montant2 montant du chèque N°2
     * @throws AffaireNotFoundException
     * @throws WrongTotalAmountException
     */
    void validerCommande(int numAffaire, float montant1, float montant2) throws AffaireNotFoundException, WrongTotalAmountException;
}
