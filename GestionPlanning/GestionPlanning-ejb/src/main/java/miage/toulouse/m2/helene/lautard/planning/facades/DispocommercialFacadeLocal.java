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
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CalendrierNotFoundException;

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
     * @param dispo dispoCommercial à mettre à jour
     * @param numAffaire affaire concernée par le rdv bloqué
     * @return DispoCommercial
     */
    Dispocommercial bloquerCreneauCommercial(Dispocommercial dispo, int numAffaire);
    
    /**
     * Récupérer l'ensemble des créneaux disponible pour un RDV commercial
     * @return Liste de créneaux disponibles
     */
    List<Dispocommercial> findCreneauxDispoCom();
    
    /**
     * Recherche une dispo commercial selon le numéro du commercial et l'id du calendrier
     * @param numCommercial numéro d'identification du commercial
     * @param numCreneau numero d'identification du calendrier
     * @return Dispocommercial
     */
    Dispocommercial findDispoByCommercialCreneau(int numCommercial, int numCreneau);
    
}
