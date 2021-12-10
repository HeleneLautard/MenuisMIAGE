/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.entities.Menuiserie;

/**
 *
 * @author Hélène
 */
@Local
public interface MenuiserieFacadeLocal {

    Menuiserie create(Menuiserie menuiserie);

    Menuiserie edit(Menuiserie menuiserie);

    void remove(Menuiserie menuiserie);

    Menuiserie find(Object id);

    List<Menuiserie> findAll();

    List<Menuiserie> findRange(int[] range);

    int count();
    
    /**
     * Création d'une menuiserie
     * @param designation nom de la menuiserie
     * @param fabricant fabricant de la menuiserie
     * @return Menuiserie
     */
    Menuiserie creerMenuiserie(String designation, String fabricant);
    
    /**
     * Vérifier l'existance d'une menuiserie
     * @param numMenuiserie numéro de la menuiserie rechercher
     * @return Menuiserie
     */
    Menuiserie findMenuiserieByNum(int numMenuiserie);
    
}
