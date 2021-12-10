/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.metier;

import javax.ejb.Local;
import javax.jms.JMSException;
import miage.toulouse.m2.helene.lautard.entities.Commande;
import miage.toulouse.m2.helene.lautard.entities.Menuiserie;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CommandeNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.MenuiserieNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException;

/**
 *
 * @author Hélène
 */
@Local
public interface GestionAchatLocal {
    
    /**
     * Création d'une menuiserie
     * @param designation désignation de la menuiserie
     * @param fabricant fabricant de la menuiserie
     * @return Menuiserie
     */
    Menuiserie creeerMenuiserie(String designation, String fabricant);
    
    /**
     * Création d'une commande d'achat
     * @param cotes cotes de la menuiserie
     * @param montant montant négocié
     * @param keynumaffaire numéro de l'affaire concernée
     * @param numMenuiserie menuiserie à commander
     * @param numClient numéro du client concerné par l'affaire
     * @return Commande
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.MenuiserieNotFoundException
     */
    Commande creerCommande(String cotes, float montant, int keynumaffaire, int numMenuiserie, int numClient) throws MenuiserieNotFoundException;
    
    
    /**
     * Recherche d'une menuiserie
     * @param numMenuiserie numéro de la menuiserie recherchée
     * @return Menuiserie
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.MenuiserieNotFoundException
     */
    Menuiserie findMenuiserie(int numMenuiserie) throws MenuiserieNotFoundException;
    
    /**
     * Recherche d'une commande
     * @param numCommande numéro de la commande recherchée
     * @return Commande
     * @throws CommandeNotFoundException 
     */
    Commande findCommande(int numCommande) throws CommandeNotFoundException;
    
    /**
     * Vérifier que le montant des deux chèques correspond au total de la commande
     * @param commande commande concernée
     * @param montant1 montant du premier chèque
     * @param montant2 montant du second chèque
     * @throws WrongTotalAmountException 
     */
    void checkTotalAmount(Commande commande, double montant1, double montant2) throws WrongTotalAmountException;
    
    /**
     * Passer une commande de menuiserie auprès du fournisseur
     * @param numCommande numéro de la commande
     * @return commande MAJ
     * @throws CommandeNotFoundException Commande inconnue
     */
    Commande passerCommandeFournisseur(int numCommande) throws CommandeNotFoundException, JMSException;
}
