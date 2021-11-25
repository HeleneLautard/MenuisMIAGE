/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.metier;

import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.entities.Affaire;
import miage.toulouse.m2.helene.lautard.entities.Client;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException;

/**
 *
 * @author Hélène
 */
@Local
public interface GestionAffaireLocal {
    
    /**
     * Création d'une affaire au début du processus de gestion
     * @param client Client rattaché à l'affaire
     * @param lieuPose Lieu d'installation de la menuiserie
     * @return Affaire créée
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException
     */
    Affaire creerAffaire(Client client, String lieuPose) throws ClientNotFoundException;
        
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
}
