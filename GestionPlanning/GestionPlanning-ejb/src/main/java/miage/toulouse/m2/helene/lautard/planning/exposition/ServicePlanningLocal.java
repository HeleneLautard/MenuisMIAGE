/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.exposition;

import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CalendrierNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CommercialNotAvailableException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CreneauNotFoundException;

/**
 *
 * @author Hélène
 */
@Local
public interface ServicePlanningLocal {
    
    /**
     * Rechercher un créneau libre pour un RDV Commercial
     * @return ensemble des créneaux disponibles avec un commercial
     */
    public String findCreneauxDispoCom();
    
    /**
     * Recherche du planning pour un commercial donné
     * @param idCommercial nuémro du commercial dont on cherche le planning
     * @return  ensemble des créneaux de son planning
     */
    public String findCreneauxCom(int idCommercial);
    
    /**
     * Réserver un créneau dans le planning pour un RDV Commercial
     * @param content contenu de la requête REST
     * @param idCommercial
     * @param idCreneau
     * @return Creéneau réservé
     * @throws CalendrierNotFoundException
     * @throws CreneauNotFoundException
     * @throws CommercialNotAvailableException 
     */
    public String bloquerCreneauCommercial(String content, int idCommercial, int idCreneau) throws CalendrierNotFoundException, CreneauNotFoundException, CommercialNotAvailableException;
    
}
