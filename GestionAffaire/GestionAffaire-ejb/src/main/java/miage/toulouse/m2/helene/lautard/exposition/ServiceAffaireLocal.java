/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.exposition;

import javax.ejb.Local;
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
     * Renseigner une commande pour une affaire
     * @param content contenu du body de la requête rest
     * @return JSON de l'affaire
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException 
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongClientException 
     */
    public String renseignerCommande(String content) throws AffaireNotFoundException, WrongClientException;
    
    /**
     * Valider une commande pour une affaire
     * @param numAffaire
     * @param content
     * @throws AffaireNotFoundException
     * @throws WrongTotalAmountException 
     */
    public void validerCommande(String numAffaire, String content) throws AffaireNotFoundException, WrongTotalAmountException;
}
