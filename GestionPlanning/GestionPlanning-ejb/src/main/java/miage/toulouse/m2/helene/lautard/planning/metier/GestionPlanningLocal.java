/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.metier;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.planning.entities.Calendrier;
import miage.toulouse.m2.helene.lautard.planning.entities.Dispocommercial;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CalendrierNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CommercialNotAvailableException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CreneauNotFoundException;

/**
 *
 * @author Hélène
 */
@Local
public interface GestionPlanningLocal {
    
    /**
     * Créaer un créenau dans le calendrier
     * @param dateHeureDeb DateHeure de début
     * @param dateHeureFin DateHeure de fin
     * @return Calendrier
     */
    Calendrier creerCreneauCalendrier(Date dateHeureDeb, Date dateHeureFin);
    
    /**
     * Création d'une disponibilité de commercial
     * @param numCommercial numéro du commercial
     * @param creneau créenau dans le calendrier
     * @return Dispocommercial
     */
    Dispocommercial creerDispoCommercial(int numCommercial, Calendrier creneau);
    
    /*
    *Renvoie la liste de tous les créneaux du calendrier
    */
    List<Calendrier> getAllCreneauxCalendrier();
    
    /**
     * Réserver un créenau dans le planning des commerciaux
     * @param numCommercial numéro du commercial
     * @param numCreneau créneau du calendrier
     * @param numAffaire numéro de l'affaire
     * @return Dispocommercial
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CalendrierNotFoundException
     * @throws miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CreneauNotFoundException
     */
    Dispocommercial bloquerCreneauCommercial(int numCommercial, int numCreneau, int numAffaire) throws CalendrierNotFoundException, CreneauNotFoundException, CommercialNotAvailableException;
    
    /**
     * Récupérer tous les créneaux disponibles pour les commerciaux
     * @return Liste de Dispocommercial
     */
    List<Dispocommercial> findCreneauxDispoCom();
    
}
