/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.exposition;

import java.util.Collection;
import javax.ejb.Local;
import javax.jms.JMSException;
import miage.toulouse.m2.helene.lautard.entities.Affaire;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongClientException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException;

/**
 *
 * @author Hélène
 */
@Local
public interface ServiceAffaireLocal {
    
    /**
     * Création d'une nouvelle affaire
     * @param content contenu de la requete
     * @return JSON de l'affaire créée
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException
     */
    public String creerAffaire(String content) throws ClientNotFoundException;
    
    /**
     * Renseigner une commande pour une affaire
     * @param content contenu du body de la requête rest
     * @return JSON de l'affaire
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException 
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongClientException 
     * @throws javax.jms.JMSException 
     */
    public String renseignerCommande(String content) throws AffaireNotFoundException, WrongClientException, JMSException;
    
    /**
     * Valider une commande pour une affaire
     * @param numAffaire
     * @param content
     * @throws AffaireNotFoundException
     * @throws WrongTotalAmountException 
     */
    public void validerCommande(String numAffaire, String content) throws AffaireNotFoundException, WrongTotalAmountException;
    
    /**
     * Récupérer une affaire selon son numéro d'identification
     * @param numAffaire numéro de l'affaire
     * @return Affaire
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException
     */
    public Affaire findAffaireByNum(int numAffaire) throws AffaireNotFoundException;
}
