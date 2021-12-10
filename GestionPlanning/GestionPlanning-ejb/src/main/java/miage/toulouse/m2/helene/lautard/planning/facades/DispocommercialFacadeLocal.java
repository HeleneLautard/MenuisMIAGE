/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.facades;

import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.planning.entities.Calendrier;
import miage.toulouse.m2.helene.lautard.planning.entities.Dispocommercial;

/**
 *
 * @author Hélène
 */
@Local
public interface DispocommercialFacadeLocal {

    Dispocommercial create(Dispocommercial dispocommercial);

    Dispocommercial edit(Dispocommercial dispocommercial);

    void remove(Dispocommercial dispocommercial);

    Dispocommercial find(Object id);

    List<Dispocommercial> findAll();

    List<Dispocommercial> findRange(int[] range);

    int count();
    
    /**
     * Création d'une disponibilité pour un commercial
     * @param numCommercial numéro du comercial concerné
     * @param creneau créneau dans le calendrier
     * @return DispoCommercial
     */
    Dispocommercial creerDispoCommercial(int numCommercial, Calendrier creneau);
    
    /**
     * Réserver un créenau dans le planning d'un commercial
     * @param numCommercial numéro d'un commercial
     * @param creneau creneau dans le calendrier
     * @param numAffaire numéro de l'affaire concernée par le RDV
     * @param numClient numéro du client concerné par l'affaire
     * @return DispoCommercial
     */
    Dispocommercial bloquerCreneauCommercial(int numCommercial, Calendrier creneau, int numAffaire, int numClient);
    
    /**
     * Récupérer l'ensemble des créneaux disponible pour un RDV commercial
     * @return Liste de créneaux disponibles
     */
    List<Dispocommercial> findCreneauxDispoCom();
    
}
