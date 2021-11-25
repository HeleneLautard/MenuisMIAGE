/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.entities.Affaire;
import miage.toulouse.m2.helene.lautard.entities.Client;

/**
 *
 * @author Hélène
 */
@Local
public interface AffaireFacadeLocal {

    Affaire create(Affaire affaire);

    void edit(Affaire affaire);

    void remove(Affaire affaire);

    Affaire find(Object id);

    List<Affaire> findAll();

    List<Affaire> findRange(int[] range);

    int count();
    
    /**
     * Création d'une affaire au début du processus de gestion
     * @param client client concerné par l'affaire
     * @param lieuPose lieu d'installation de l'affaire
     * @return Affaire créée
     */
    Affaire creerAffaire(Client client, String lieuPose);
    
}
