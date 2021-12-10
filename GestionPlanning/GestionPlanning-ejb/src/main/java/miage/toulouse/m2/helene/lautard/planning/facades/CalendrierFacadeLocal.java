/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.facades;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.planning.entities.Calendrier;

/**
 *
 * @author Hélène
 */
@Local
public interface CalendrierFacadeLocal {

    Calendrier create(Calendrier calendrier);

    Calendrier edit(Calendrier calendrier);

    void remove(Calendrier calendrier);

    Calendrier find(Object id);

    List<Calendrier> findAll();

    List<Calendrier> findRange(int[] range);

    int count();
    
    /**
     * Création d'un nouveau créenau dans le calendrier
     * @param dateHeureDeb Date et heure de début
     * @param dateHeureFin Date et heure de début
     * @return Calendrier
     */
    Calendrier creerCreneauCalendrier(Date dateHeureDeb, Date dateHeureFin);
    
}
