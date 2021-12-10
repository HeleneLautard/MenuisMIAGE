/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.entities.Commande;
import miage.toulouse.m2.helene.lautard.entities.Menuiserie;

/**
 *
 * @author Hélène
 */
@Local
public interface CommandeFacadeLocal {

    Commande create(Commande commande);

    Commande edit(Commande commande);

    void remove(Commande commande);

    Commande find(Object id);

    List<Commande> findAll();

    List<Commande> findRange(int[] range);

    int count();
    
    /**
     * Création d'une commande de menuiserie
     * @param cotes cotes de la menuiserie
     * @param montant montant négocié
     * @param keynumaffaire numéro de l'affaire concernée par la commande
     * @param menuis menuiserie à commander
     * @return Commande
     */
    Commande creerCommande(String cotes, float montant, int keynumaffaire, Menuiserie menuis, int numClient);
    
    /**
     * Rechercher une commande selon son numéro
     * @param numCommande numéro de commande
     * @return Commande
     */
    Commande findCommandeByNum(int numCommande);
    
    /**
     * Valider la commande
     * @param numCommande numéro de la commande
     * @return  Commande MAJ
     */
    Commande validerCommande(int numCommande);
    
}
